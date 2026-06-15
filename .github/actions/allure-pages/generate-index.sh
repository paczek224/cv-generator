#!/usr/bin/env bash
# Builds the index page (index.html) listing all Allure reports
# grouped by branch and commit.
# Usage: generate-index.sh <gh-pages-dir> <owner/repo>
set -euo pipefail

ROOT="${1:-.}"
REPO="${2:-}"
OUT="${ROOT}/index.html"

{
  cat <<HTML
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Allure Reports — ${REPO}</title>
<style>
  :root{ --bg:#0f1117; --card:#171a23; --fg:#e6e8ee; --muted:#8b90a0; --accent:#36c98b; --line:#262b38; }
  *{ box-sizing:border-box; }
  body{ margin:0; font-family:system-ui,-apple-system,Segoe UI,Roboto,Arial,sans-serif; background:var(--bg); color:var(--fg); }
  .wrap{ max-width:880px; margin:0 auto; padding:40px 20px 80px; }
  h1{ font-size:1.6rem; margin:0 0 4px; }
  .meta{ color:var(--muted); font-size:.85rem; margin-bottom:28px; }
  .branch{ background:var(--card); border:1px solid var(--line); border-radius:12px; padding:16px 18px; margin:0 0 16px; }
  .branch h2{ font-size:1.05rem; margin:0 0 10px; display:flex; align-items:center; gap:8px; }
  .branch h2 .tag{ font-size:.72rem; color:var(--muted); font-weight:400; }
  ul{ list-style:none; margin:0; padding:0; }
  li{ display:flex; align-items:center; gap:10px; padding:7px 0; border-top:1px solid var(--line); }
  li:first-child{ border-top:none; }
  a.sha{ color:var(--accent); text-decoration:none; font-family:ui-monospace,SFMono-Regular,Menlo,monospace; font-size:.92rem; }
  a.sha:hover{ text-decoration:underline; }
  .empty{ color:var(--muted); }
  footer{ color:var(--muted); font-size:.78rem; margin-top:30px; }
  a{ color:var(--accent); }
</style>
</head>
<body>
<div class="wrap">
  <h1>Allure Reports</h1>
  <div class="meta">${REPO}</div>
HTML

  shopt -s nullglob
  any_branch=0
  for bdir in "${ROOT}"/*/; do
    b="$(basename "$bdir")"

    # is there any <sha>/allure report in this branch?
    has_report=0
    for sdir in "${bdir}"*/; do
      [ -d "${sdir}allure" ] && { has_report=1; break; }
    done
    [ "$has_report" -eq 1 ] || continue
    any_branch=1

    echo "  <div class=\"branch\">"
    echo "    <h2>${b} <span class=\"tag\">branch</span></h2>"
    echo "    <ul>"

    # commits from newest (by modification time)
    for sdir in $(ls -1dt "${bdir}"*/ 2>/dev/null); do
      s="$(basename "$sdir")"
      [ -d "${sdir}allure" ] || continue
      echo "      <li><a class=\"sha\" href=\"./${b}/${s}/allure/\">${b}/${s}</a></li>"
    done

    echo "    </ul>"
    echo "  </div>"
  done

  if [ "$any_branch" -eq 0 ]; then
    echo "  <p class=\"empty\">No published reports.</p>"
  fi

  cat <<HTML
  <footer>Generated automatically by the allure-pages action.</footer>
</div>
</body>
</html>
HTML
} > "$OUT"

echo "Generated: $OUT"
