# Différence entre les versions du standard (SEDA 2.1 / FNTC / Modèle)

Liste des champs dans les différentes versions du standard. Présence dans le modèle pivot et conversion dans les services de la bibliothèque SEDA 2.1 et FNTC-TA.

Tous les champs facultatifs du SEDA 2.1 ne sont pas intégrés à cette grille car ils ne sont pas forcément utiles.

Exemple de structure à intégrer dans le bloc Physical.

Travail en cours. Traduction à prévoir.

<u>To Do</u> : Vérifier que toutes les balises du Converter V4 sont bien dans le XSD FNTC V4.

### Physical

| SEDA de base       | Modèle bibliothèque | Converter SEDA 2.1 | Converter FNTC V4 |
| ------------------ | ------------------- | ------------------ | ----------------- |
| PhysicalId         | physicalId          | implémenté         | implémenté        |
|                    | measure             | pas à implémenter  | implémenté        |
| PhysicalDimensions | à implémenter       | à implémenter      | pas à implémenter |
| DataObjectVersion  | à implémenter       | à implémenter      | pas à implémenter |



### Binary

| SEDA de base      | Modèle bibliothèque | Converter SEDA 2.1 | Converter FNTC V4     |
| ----------------- | ------------------- | ------------------ | --------------------- |
| DataObjectVersion | objectVersion       | implémenté         | pas à implémenter     |
| FormatId          | formatId            | implémenté         | implémenté (<Format>) |
| FormatLitteral    | formatName          | implémenté         | pas à implémenter     |
| MimeType          | mimeType            | implémenté         | pas à implémenter     |
|                   | signatureStatus     | pas à implémenter  | SignatureStatus       |
| MessageDigest     | digestAlgorithm     | implémenté         | implémenté            |
| Filename          | binaryName          | implémenté         | implémenté            |
| FileInfo          | fileInfo            | implémenté         | pas à implémenter     |

### Management

| SEDA de base              | Modèle bibliothèque         | Converter SEDA 2.1       | Converter FNTC V4        |
| ------------------------- | --------------------------- | ------------------------ | ------------------------ |
| AccessRule                | accessRule                  | implémenté               | implémenté               |
| AccessRule/Rule           | accessRule/rule             | implémenté               | implémenté               |
| AccessRule/StartDate      | accessRule/startDate        | implémenté               | implémenté               |
| AppraisalRule             | appraisalRule               | implémenté               | implémenté               |
| AppraisalRule/FinalAction | appraisalRule/appraisalCode | implémenté               | implémenté               |
| AppraisalRule/Rule        | appraisalRule/duration      | implémenté               | implémenté               |
| AppraisalRule/StartDate   | appraisalRule/startDate     | implémenté               | implémenté               |
|                           | updateOperation             | à implémenter (extended) | à implémenter (extended) |
| LogBook/Event             | logEvents                   | implémenté               | implémenté               |
| DisseminationRule         |                             | à implémenter            | pas à implémenter        |
| ReuseRule                 |                             | à implémenter            | pas à implémenter        |
| StorageRule               |                             | à implémenter            | pas à implémenter        |

### Content (ArchiveUnit)

| SEDA de base                             | Modèle bibliothèque                     | Converter SEDA 2.1 | Converter FNTC V4 |
| ---------------------------------------- | --------------------------------------- | ------------------ | ----------------- |
| DescriptionLevel                         | descriptionLevel                        | implémenté         | implémenté        |
| Title/@lang                              | titles                                  | implémenté         | implémenté        |
| Description                              | descriptions                            | implémenté         | implémenté        |
| FilePlanPosition                         | filePlanPosition                        | implémenté         | pas besoin        |
|                                          | systemId                                | implémenté         | implémenté        |
| DataObjectSystemId                       | dataObjectSystemId                      | implémenté         | implémenté        |
|                                          | originatingSystemId                     | implémenté         | implémenté        |
| ArchivalAgency ArchiveUnitIdentifier     | archivalAgencyArchiveUnitIdentifier     | implémenté         | implémenté        |
| OriginatingAgency ArchiveUnitIdentifier  | originatingAgencyArchiveUnitIdentifier  | implémenté         | implémenté        |
| TransferringAgency ArchiveUnitIdentifier | transferringAgencyArchiveUnitIdentifier | implémenté         | implémenté        |
| Type                                     | type                                    | implémenté         | pas besoin        |
| DocumentType                             | documentType                            | implémenté         | implémenté        |
| Language                                 | languages                               | implémenté         | implémenté        |
| DescriptionLanguage                      | descriptionLanguage                     | implémenté         | implémenté        |
| Status                                   | status                                  | implémenté         | implémenté        |
| Version                                  | version                                 | implémenté         | implémenté        |
| Tag                                      | tags                                    | implémenté         | implémenté        |
| Keyword                                  |                                         | à implémenter      | pas à implémenter |
| OriginatingAgency                        | originatingAgency                       | implémenté         | implémenté        |
| SubmissionAgency                         | submissionAgency                        | implémenté         | implémenté        |
| AuthorizedAgent                          | authorizedAgents                        | implémenté         | implémenté        |
| Writer                                   | writers                                 | implémenté         | implémenté        |
| CreatedDate                              | createdDate                             | implémenté         | implémenté        |
| TransactedDate                           | transactedDate                          | implémenté         | implémenté        |
| AcquiredDate                             | acquiredDate                            | implémenté         | implémenté        |
| SentDate                                 | sentDate                                | implémenté         | implémenté        |
| ReceivedDate                             | receivedDate                            | implémenté         | implémenté        |
| RegisteredDate                           | registeredDate                          | implémenté         | implémenté        |
| StartDate                                | startDate                               | implémenté         | implémenté        |
| EndDate                                  | endDate                                 | implémenté         | implémenté        |

