/**
 * Provides the frontend of terminal
 *
 * @since 0.0.5
 */

import React, { useState, useRef, useEffect } from 'react';
import './../style/terminal.css';

interface APIResponse<T> {
    status: 'SUCCESS' | 'SUCCESS_OPERATION' | 'FAILURE' | 'WARN' | 'UNDEFINED';
    data: T | null;
    message: string | null;
}

const Terminal: React.FC = () => {

    // const dynamicSpanRef = useRef<HTMLDivElement>(null);
    const inputRef = useRef<HTMLDivElement>(null);
    const terminalRef = useRef<HTMLDivElement>(null);

    const [output, setOutput] = useState<String[]>([]);
    const [history, setHistory] = useState<string[]>([]);
    const [historyIndex, setHistoryIndex] = useState<number>(-1);
    
    const prompt = 'user@localhost:~$ ';
    const promptInc = '> ';
    
    const [promptCurrent, setPrompt] = useState<String>(prompt);
    const [outputInc, setOutputInc] = useState<String>('');

    useEffect(() => {
        if (terminalRef.current) {
            terminalRef.current.scrollTop = terminalRef.current.scrollHeight;
        }
    }, [output]);
    
    useEffect(() => {
        if (inputRef.current) {
            inputRef.current.focus();
        }
    }, []);

    useEffect(() => {
        const handleBlur = () => {
            if (inputRef.current) {
                inputRef.current.focus();
            }
        };

        const inputElement = inputRef.current;

        if (inputElement) {
            inputElement.addEventListener('blur', handleBlur);
        }

        return () => {
            if (inputElement) {
                inputElement.removeEventListener('blur', handleBlur);
            }
        };
    }, []);

    const handleInput = async(e: React.KeyboardEvent<HTMLDivElement>) => {

        if (e.key === 'Enter') {
            e.preventDefault();
            const commandLine = inputRef.current?.innerText.trim();
            if (commandLine) {
                appendOutput(`${promptCurrent}${commandLine}\n`);
                updateHistory(commandLine);
                console.log("processCommand", outputInc + commandLine);
                await processCommand(outputInc + commandLine);
                if (inputRef.current) {
                    inputRef.current.innerText = '';
                }
            } else {
                appendOutput(`${promptCurrent}${commandLine}\n`);
            }
        } else if (e.key === 'ArrowUp' || e.key === 'ArrowDown') {
            e.preventDefault();
            navigateHistory(e.key);
        }

    };

    const appendOutput = (text: string) => {
        setOutput((prev) => [...prev, text]);
    };

    const clearOutput = () => {
        setOutput([]);
    };
  
    const updateHistory = (commandLine: string) => {
        setHistory([...history, commandLine]);
        setHistoryIndex(-1);
    };

    const updatePrompt = (prompt: string) => {
        setPrompt(prompt);
    };

    const updateOutputInc = (out: string) => {
        setOutputInc(out);
    };

    const clearOutputInc = () => {
        setOutputInc('');
    };
      
  
    const navigateHistory = (key: string) => {
        if (history.length === 0) return;
  
        let index = historyIndex;
  
        if (key === 'ArrowUp') {
            index = index <= 0 ? history.length - 1 : index - 1;
        } else if (key === 'ArrowDown') {
            index = index >= history.length - 1 ? 0 : index + 1;
        }
  
        setHistoryIndex(index);
        const commandLine = history[index];
        if (inputRef.current) {
            inputRef.current.innerText = commandLine;
        }
    };

    

    const processCommand = async (commandLine: string) => {
        // const [command, ...argsArray] = commandLine.trim().split(/\s+/);
        // const args = argsArray.join(' ');

        const firstSpaceIndex = commandLine.indexOf(' ');

        let command;
        let args;

        if(firstSpaceIndex === -1) {
            command = commandLine;
            args = '';
        } else {
            command = commandLine.slice(0, firstSpaceIndex);
            args = commandLine.slice(firstSpaceIndex + 1);
        }

        try {
            const response = await fetch('http://localhost:8080/api/execute', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ command, args }),
            });

            console.log("response.body:", JSON.stringify({ command, args }));


            const result: APIResponse<string> = await response.json();

            if(result.status === 'SUCCESS') {

                updatePrompt(prompt);
                clearOutputInc();

                let dataOutput = '';

                console.log('result:', result);
                console.log('result.data:', result.data);
  
                if (result.data === null) {
                    dataOutput = 'null';
                } else if (result.data === undefined) {
                    dataOutput = 'undefined';
                } else if (typeof result.data === 'string') {
                    dataOutput = result.data;
                } else if (typeof result.data === 'number' || typeof result.data === 'boolean') {
                    dataOutput = String(result.data);
                } else if (typeof result.data === 'object') {
                    dataOutput = JSON.stringify(result.data, null, 2); // Pretty-print JSON
                } else {
                    dataOutput = 'Unsupported data type received.\n';
                }
                appendOutput(dataOutput);

            } else if(result.status === 'SUCCESS_OPERATION') {

                console.log('result:', result);
                console.log('result.data:', result.data);
                console.log('result.message:', result.message);

                if(result.data === 'clear') {
                    clearOutput();
                } else if(result.data === 'incomplete') {
                    updatePrompt(promptInc);
                    updateOutputInc(result.message || '');
                } else {
                    appendOutput(`Error: APIResponse Has no Operation ${result.data}\n`);
                }

            } else if(result.status === 'FAILURE') {
                console.log(result.message);
                appendOutput(`Error: ${result.message || 'An error occurred.\n'}`);
            } else if(result.status === 'WARN') {
            console.log(result.message);
            appendOutput(`Warning: ${result.message || 'Command executed with warnings.\n'}`);
            } else {
                appendOutput('Received an undefined status from the server.\n');
            }
        } catch (error: any) {
            appendOutput(`Error: ${error.message}\n`);
        }
    };

    return (
        <div className='terminal' ref={terminalRef}>
            <div className='container'>
                {output.map((line, index) => (
                    <span key={index} className='dynamic-span'>{line}</span>
                ))}
                <span className='dynamic-span'>{promptCurrent}</span>
                <span
                    className='dynamic-span'
                    contentEditable
                    spellCheck={false}
                    onKeyDown={handleInput}
                    ref={inputRef}
                    data-placeholder=""
                />
            </div>
        </div>
    );

};

export default Terminal;