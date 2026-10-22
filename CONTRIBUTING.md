# Contributing

Keep this exercise focused on REST fundamentals. Prefer small changes with explicit behaviour over adding infrastructure without a requirement.

## Verification

Use the README's proposed wrapper commands and report actual results. The existing contextLoads test does not verify endpoint behaviour. Add focused request/response tests for non-trivial changes.

Useful cases include an empty list, assigned IDs, missing records, duplicate names, case-insensitive exact search, null names, malformed JSON and PUT with omitted fields. Check both HTTP status and the wrapper's JSON status type. For concurrency changes, test shared-list behaviour rather than assuming atomic IDs protect the whole operation.

Use synthetic person data. Preserve unrelated endpoint behaviour and keep generated target files out of commits.

## Pull requests

Describe the problem, focused change, checks actually run and remaining gaps. Separate documentation edits from API-contract changes. Follow [SECURITY.md](SECURITY.md) for sensitive reports.
