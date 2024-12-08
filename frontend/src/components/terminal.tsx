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

    useEffect(() => {
        if (terminalRef.current) {
            terminalRef.current.scrollTop = terminalRef.current.scrollHeight;
        }
    }, [output]);

    // useEffect(() => {

    //     if(dynamicSpanRef.current) {
            
    //         // console.log('Scroll Height:', document.documentElement.scrollHeight);
            
    //         // window.scrollTo({
    //         //     top: document.body.scrollHeight,
    //         //     // behavior: 'smooth',
    //         // });

    //         // dynamicSpanRef.current.scrollTop = dynamicSpanRef.current.scrollHeight;

    //         const container = dynamicSpanRef.current;
    //         const spans = container.querySelectorAll<HTMLSpanElement>('.dynamic-span');

    //         const containerWidth = container.offsetWidth;
    //         var currentWidth = 0;

    //         // // Splits the spans if reaches maximum console width
    //         // spans.forEach((span) => {

    //         //     const spanWidth = span.offsetWidth;
    //         //     currentWidth = (currentWidth + spanWidth) % containerWidth;

    //         //     if(currentWidth > containerWidth) {
    //         //         span.classList.add('breaked-span');
    //         //     } else {
    //         //         span.classList.remove('breaked-span');
    //         //     }

    //         // });

    //     }

    // }, [output]);

    const handleInput = async(e: React.KeyboardEvent<HTMLDivElement>) => {

        if (e.key === 'Enter') {
            e.preventDefault();
            const commandLine = inputRef.current?.innerText.trim();
            if (commandLine) {
                appendOutput(`${prompt}${commandLine}\n`);
                updateHistory(commandLine);
                await processCommand(commandLine);
                if (inputRef.current) {
                    inputRef.current.innerText = '';
                }
            } else {
                appendOutput(`${prompt}${commandLine}\n`);
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
        const [command, ...argsArray] = commandLine.trim().split(/\s+/);
        const args = argsArray.join(' ');

        try {
            const response = await fetch('http://localhost:8080/api/execute', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ command, args }),
            });


            const result: APIResponse<string> = await response.json();

            if(result.status === 'SUCCESS') {
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

                if(result.data === 'clear') {
                    clearOutput();
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
            appendOutput(`Error: ${error.message}`);
        }
    };

    return (
        <div className='terminal' ref={terminalRef}>
            <div className='container'>
                {output.map((line, index) => (
                    <span key={index} className='dynamic-span'>
                        {line}
                    </span>
                ))}
                <span className='dynamic-span'>{prompt}</span>
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