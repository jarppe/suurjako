/* jshint node: true */
var gulp = require("gulp"),
    plumber = require("gulp-plumber")
    watch = require("gulp-watch"),
    less = require("gulp-less"),
    webserver = require("gulp-webserver");

gulp.task("less-prod", function() {
  gulp.src("src/less/suurjako.less")
    .pipe(plumber())
    .pipe(less({
      compress: true,
      paths: ["src/less", "bower_components"],
    }))
    .pipe(gulp.dest("app"));
});

gulp.task("less-dev", function() {
  gulp.src("src/less/suurjako.less")
    .pipe(plumber())
    .pipe(less({
      paths: ["src/less", "bower_components"],
    }))
    .pipe(gulp.dest("target/dev/app"));
});

gulp.task("server", function() {
  gulp.src("index.html")
    .pipe(gulp.dest("target/dev"));
  gulp.src("bower_components/fontawesome/fonts/*")
    .pipe(gulp.dest("target/dev/fonts"));
  gulp.watch("src/less/**/*.less", ["less-dev"]);
  gulp.src("target/dev")
    .pipe(webserver({
      port:        8080,
      livereload:  true
  }));
});

gulp.task("dist", ["less-prod"], function() {
  gulp.src("bower_components/fontawesome/fonts/*")
    .pipe(gulp.dest("fonts"))
});

gulp.task("default", ["less-dev", "server"]);
