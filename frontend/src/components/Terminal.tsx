import React, { useState, useRef, useEffect } from 'react';
import './../style/Terminal.css';

interface APIResponse<T> {
  status: 'SUCCESS' | 'FAILURE' | 'WARN' | 'UNDEFINED';
  data: T | null;
  message: string | null;
}

const Terminal: React.FC = () => {
  const [output, setOutput] = useState<string[]>([]);
  const [history, setHistory] = useState<string[]>([]);
  const [historyIndex, setHistoryIndex] = useState<number>(-1);
  const terminalRef = useRef<HTMLDivElement>(null);
  const inputRef = useRef<HTMLDivElement>(null);

  const prompt = 'user@localhost:~$ ';

  useEffect(() => {
    if (terminalRef.current) {
      terminalRef.current.scrollTop = terminalRef.current.scrollHeight;
    }
  }, [output]);

  const handleInput = async (e: React.KeyboardEvent<HTMLDivElement>) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      const commandLine = inputRef.current?.innerText.trim();
      if (commandLine) {
        appendOutput(`${prompt}${commandLine}`);
        updateHistory(commandLine);
        await processCommand(commandLine);
        if (inputRef.current) {
          inputRef.current.innerText = '';
        }
      }
    } else if (e.key === 'ArrowUp' || e.key === 'ArrowDown') {
      e.preventDefault();
      navigateHistory(e.key);
    }
  };

  const appendOutput = (text: string) => {
    setOutput((prev) => [...prev, text]);
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

    //   if (!response.ok) {
    //     throw new Error(`Network response was not ok: ${response.statusText}`);
    //   }

      const result: APIResponse<string> = await response.json();

      if (result.status === 'SUCCESS') {
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
          dataOutput = 'Unsupported data type received.';
        }
        appendOutput(dataOutput);
      } else if (result.status === 'FAILURE') {
        appendOutput(`Error: ${result.message || 'An error occurred.'}`);
      } else if (result.status === 'WARN') {
        appendOutput(`Warning: ${result.message || 'Command executed with warnings.'}`);
      } else {
        appendOutput('Received an undefined status from the server.');
      }
    } catch (error: any) {
      appendOutput(`Error: ${error.message}`);
    }
  };

  return (
    <div className="terminal" ref={terminalRef}>
      {output.map((line, index) => (
        <div key={index} className="output-line">
          {line}
        </div>
      ))}
      <div className="input-line">
        <span className="prompt">{prompt}</span>
        <div
          className="input-area"
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
