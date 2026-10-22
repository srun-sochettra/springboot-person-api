# Security and demo-use guidance

This is a local in-memory coursework API, not a hardened person-record service. No security audit, supported-version programme or response deadline is established here.

The supplied endpoints have no application authentication or authorisation. Do not expose them with real personal records. Field constraints, maximum lengths and application-level abuse controls are not implemented in the supplied controller/model.

The shared ArrayList is not synchronised; AtomicInteger protects ID allocation only. Null names can fail search. Process restart loses all records. No durable audit, backup, recovery or deletion-retention workflow is supplied.

Use synthetic names and attributes in requests, screenshots and reports. No private credentials are required by the supplied application configuration. Review staged files before adding future environment settings; the ignore rules do not explicitly exclude a plain .env file.

For sensitive concerns, use a verified private maintainer channel if available, or request one without posting details publicly. No dedicated reporting address or enabled private-reporting feature was confirmed. These are static source observations, not an exhaustive audit or fixes.
