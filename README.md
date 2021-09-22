# cljs-reagent-reframe-tailwind-boilerplate

An opinionated boilerplate for Reagent app.

- [shadow-cljs](https://github.com/thheller/shadow-cljs)
- [reagent](https://github.com/reagent-project/reagent) (react on dev mode & preact on production)
- [re-frame](https://github.com/day8/re-frame)
- [twind](https://twind.dev)

### Installation

```bash
npm install
# or 
yarn install
```

### Development

```bash
npm run dev
# served to `http://localhost:5000`.

# or with the netlify-cli
ntl dev
```

### Build and serve on production mode

On production mode React is replaced by Preact

```bash
npm run build
npm run serve

# served to `http://localhost:5000`.
```
