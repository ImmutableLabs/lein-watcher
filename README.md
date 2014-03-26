# lein-watch

A Leiningen plugin to watch and run clojure files which are meant to compiled to another file type. For example: generating html from [hiccup](https://github.com/weavejester/hiccup), Creating css from [garden](https://github.com/noprompt/garden), or creating .csv from clojure code.

## Usage


Put `[lein-watch "0.1.0"]` into the `:plugins` vector of your project.clj.

Add a configuration to your project.clj specifying the directory to watch, where to place the output and what filetype they should appear as.
```
:lein-watch {:input-dir "src/hello-world/svgs/" 
             :output-dir "assets/svgs/"
             :output-type "svg"}

```

Then call
```
$ lein watch
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
