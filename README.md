# zodiac/app template

A [deps-new](https://github.com/seancorfield/deps-new) template for creating
[Zodiac](https://github.com/brettatoms/zodiac) web applications.

The generated project includes:
- **zodiac** — web framework (reitit, ring, jetty, chassis HTML)
- **zodiac-assets** — Vite integration for CSS/JS assets
- **zodiac-sql** — Database access (HoneySQL + next.jdbc + HikariCP)
- **zodiac-headers** — Secure HTTP response headers
- **zodiac-hot-reload** — Code reloading during development
- **Alpine.js** + **htmx** — Frontend interactivity
- **Tailwind CSS** — Utility-first CSS framework

## Usage

Install deps-new as a tool:

```bash
clojure -Ttools install-latest :lib io.github.seancorfield/deps-new :as new
```

### Basic (flat structure + SQLite)

```bash
clojure -Sdeps '{:deps {io.github.brettatoms/zodiac-template {:git/tag "v0.1.0" :git/sha "FIXME"}}}' \
  -Tnew create :template zodiac/app :name myorg/myapp
```

### With PostgreSQL

```bash
clojure -Sdeps '{:deps {io.github.brettatoms/zodiac-template {:git/tag "v0.1.0" :git/sha "FIXME"}}}' \
  -Tnew create :template zodiac/app :name myorg/myapp :db :postgres
```

### Polylith structure

```bash
clojure -Sdeps '{:deps {io.github.brettatoms/zodiac-template {:git/tag "v0.1.0" :git/sha "FIXME"}}}' \
  -Tnew create :template zodiac/app :name myorg/myapp :structure :polylith
```

### Polylith + PostgreSQL

```bash
clojure -Sdeps '{:deps {io.github.brettatoms/zodiac-template {:git/tag "v0.1.0" :git/sha "FIXME"}}}' \
  -Tnew create :template zodiac/app :name myorg/myapp :structure :polylith :db :postgres
```

## Options

| Option | Values | Default | Description |
|---|---|---|---|
| `:db` | `:sqlite`, `:postgres` | `:sqlite` | Database driver and SQL dialect |
| `:structure` | `:flat`, `:polylith` | `:flat` | Project layout style |
| `:zodiac-version` | version string | `"RELEASE"` | Zodiac core version |
| `:zodiac-assets-version` | version string | `"RELEASE"` | zodiac-assets version |
| `:zodiac-sql-version` | version string | `"RELEASE"` | zodiac-sql version |
| `:zodiac-headers-version` | version string | `"RELEASE"` | zodiac-headers version |
| `:zodiac-hot-reload-version` | version string | `"RELEASE"` | zodiac-hot-reload version |

## Development

Run template tests:

```bash
clojure -M:test -m cognitect.test-runner
```

To test the template locally during development, use `:local/root`:

```bash
clojure -Sdeps '{:deps {io.github.brettatoms/zodiac-template {:local/root "."}}}' \
  -Tnew create :template zodiac/app :name myorg/myapp
```

## License

Copyright © 2026 Brett Adams

Distributed under the MIT License. See [LICENSE](LICENSE) for details.
