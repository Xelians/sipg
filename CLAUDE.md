# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

SipG (SIP Generator) is a Java 21 library for generating and validating digital archives in French archival standards: SEDA v2.1/v2.2/v2.3 and FNTC v4. It produces ZIP archives containing an XML manifest and binary content files. Published to Maven Central under `fr.xelians:sipg`.

## Build & Test Commands

```bash
# Build and run all tests
mvn -B package

# Run a single test class
mvn test -Dtest=Sedav22Test

# Run a single test method
mvn test -Dtest=Sedav22Test#testWriteMiniSip

# Regenerate JAXB classes from SEDA schemas (rarely needed)
mvn clean generate-sources -Pseda21   # or -Pseda22, -Pseda23

# Build fat JAR for GitHub release
mvn verify -Pgithub

# Build with javadoc + GPG signing for Maven Central
mvn verify -Pcentral
```

## Architecture

### Package Structure

- `fr.xelians.sipg.model` — Domain models: `ArchiveTransfer`, `ArchiveUnit`, `BinaryDataObject` variants, management rules, agents, events. Uses Jackson annotations for JSON support.
- `fr.xelians.sipg.service.sedav2` — SEDA v2 service with version-specific subpackages (`seda21/`, `seda22/`, `seda23/`). Each version has an Adapter and Converter.
- `fr.xelians.sipg.service.fntcv4` — FNTC v4 archive generation/validation.
- `fr.xelians.sipg.service.json` — JSON serialization/deserialization of ArchiveTransfer.
- `fr.xelians.sipg.utils` — Utilities: `SipUtils` (ZIP/XML/date helpers), `DroidUtils` (file format identification), `Validators` (RNG schema validation).
- `fr.gouv.culture.archivesdefrance.seda.v2*` — JAXB-generated classes from SEDA XSD schemas (do not edit manually).
- `org.afnor.medona.v1` / `org.fntc.ta.v4` — JAXB-generated classes for FNTC/MEDONA (do not edit manually).

### Key Patterns

- **Services are singletons**: `Sedav2Service.getInstance()`, `Sedav2Service.getV22Instance()`, `Sedav2Service.getV23Instance()`, `JsonService.getInstance()`, `Fntcv4Service.getInstance()`.
- **Adapter pattern**: `SedaAdapter` interface with version-specific implementations abstracts SEDA version differences.
- **Builder pattern**: `SedaConfigBuilder`, `AgentBuilder`, `EventBuilder`, `PlaceBuilder`, `FileInfoBuilder`, etc.
- **Configuration records**: `SedaConfig`, `JsonConfig`, `Fntcv4Config` are immutable Java records created via builders.
- **Progress callbacks**: `ProgressListener`/`ProgressEvent` for validation step tracking.

### Processing Flow

Generation: build domain model → service `write()` → converter transforms to JAXB types → marshal to XML → package as ZIP with `Content/` directory for binaries.

Validation: load ZIP → parse manifest XML → validate against XSD/RNG → verify binary existence/size/digest → report via ProgressListener.

## Testing

- JUnit 5 with `@ExtendWith(TestInit.class)` — creates `target/test-results/` output directory.
- `SipFactory` — factory methods for test SIP creation (`createMiniSip()`, `createComplexSip()`, etc.).
- `TestUtils` — logging and assertion helpers.
- Tests create actual ZIP archives and validate them round-trip.
- Test resources in `src/test/resources/`: sample PDFs, JSON fixtures (`minisip.json`, `deepsip.json`), SEDA/FNTC schemas and RNG profiles.

## Key Dependencies

- Jakarta JAXB 4.0 (XML binding), Jackson 2.21 (JSON), ibis-xerces (XSD 1.1 validation), Jing (RNG validation)
- DROID 6.9 (file format identification), BouncyCastle (cryptography)
- Apache Commons: compress, io, lang3, codec
