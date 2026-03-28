# {{raw-name}}

A web application built with [Zodiac](https://github.com/brettatoms/zodiac).

## Prerequisites

- [Clojure CLI](https://clojure.org/guides/install_clojure)
- [Node.js](https://nodejs.org/) (for asset compilation)

## Getting Started

Install JavaScript dependencies:

```bash
npm install
```

Start a REPL:

```bash
clj -A:dev
```

Then in the REPL:

```clojure
(go)       ; start the server
(restart)  ; restart the server
(stop)     ; stop the server
```

The app will be running at [http://localhost:3000](http://localhost:3000).

## Running Tests

```bash
clj -M:test:test-runner
```

## License

Distributed under the MIT License.
