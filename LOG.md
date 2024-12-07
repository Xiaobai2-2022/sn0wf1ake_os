# Development Log

## V 0.0.0 - 0.0.4

### Notes

#### Frontend

To start the frontend web app, use command

```shell
npm start
```

#### Backend

To start the backend server, use command

```shell
./mvnw spring-boot:run
```

WebMcvConfigurer is an interface provided by the **Spring Framework** in the **Spring MVC** module.

CORS stands for **Cross-Origin Resource Sharing** it is an HTTP-header base mechanism that allows server to indicate any origins other than its own.

To test the backend server, use command

```shell
curl -X POST -H "Content-Type: application/json" -d '{"command": "FTP", "args": "Test Arguments"}' http://localhost:8080/api/execute
```

---

#### Database

To connect to the MySQL database, use command

```shell
mysql -u root -p
```

## V 0.0.5

### Dynamic Span

```typescript

import React, { useEffect, useRef } from 'react';
import './App.css';

const App: React.FC = () => {
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (containerRef.current) {
      const container = containerRef.current;
      const spans = container.querySelectorAll<HTMLSpanElement>('.dynamic-span');

      spans.forEach((span) => {
        const spanWidth = span.offsetWidth;
        const containerWidth = container.offsetWidth;

        // Add 'breakable' class if the span exceeds container width
        if (spanWidth > containerWidth) {
          span.classList.add('breakable');
        } else {
          span.classList.remove('breakable');
        }
      });
    }
  }, []);

  return (
    <div className="text-container" ref={containerRef}>
      <span className="dynamic-span">SPAN1</span>
      <span className="dynamic-span">SPAN2</span>
      <span className="dynamic-span">SPAN3</span>
      <span className="dynamic-span">SPAN4</span>
      <span className="dynamic-span">SPAN5</span>
      <span className="dynamic-span">SPAN6</span>
    </div>
  );
};

export default App;

```

```css

/* Container for all spans */
.text-container {
  display: inline-block; /* Keep spans inline */
  max-width: 300px; /* Example: adjust width to see breaks */
  border: 1px solid #ccc; /* Optional border for visualizing */
  font-family: Arial, sans-serif;
  word-wrap: break-word; /* Allow breaking for long text */
  overflow-wrap: break-word; /* Fallback for older browsers */
  padding: 10px;
}

/* Default dynamic spans */
.dynamic-span {
  display: inline; /* Keep spans inline */
  white-space: normal; /* Allow wrapping */
  word-break: break-word; /* Break text inside the span */
}

/* Breakable spans for overflow behavior */
.breakable {
  display: inline-block; /* Break dynamically based on container */
  white-space: normal;
  word-break: break-word;
  overflow-wrap: break-word;
}

```
