# Le standard SEDA JSON 2.0

Ce document décrit de manière exhaustive le standard SEDA JSON 2.0 tel qu'implémenté par SipG
(`fr.xelians.sipg.service.sedajson.SedaJsonService`). Le standard SEDA JSON est une transposition
en JSON de l'ontologie du standard SEDA v2 : il reprend la sémantique des éléments XML en
l'adaptant aux idiomes JSON.

## Sommaire

* [Principes de conception](#principes-de-conception)
* [Structure du SIP](#structure-du-sip)
* [Ordre des clés](#ordre-des-clés)
* [Référence des clés](#référence-des-clés)
* [Textes multilingues](#textes-multilingues)
* [Éléments étendus](#éléments-étendus)
* [Constructions non représentables](#constructions-non-représentables)
* [Contraintes et limites](#contraintes-et-limites)
* [Exemple complet](#exemple-complet)
* [Validation](#validation)
* [Utilisation de l'API](#utilisation-de-lapi)

## Principes de conception

* **Pas d'identifiants ni de références.** Contrairement au XML (attributs `id`,
  `DataObjectGroup` déclarés en tête de fichier puis référencés par `DataObjectReference`,
  références inter-unités `ArchiveUnitRefId`), le JSON ne comporte aucun mécanisme de référence :
  * les objets numériques et physiques sont déclarés **directement dans leur unité d'archive**
    (`BinaryDataObjects`, `PhysicalDataObjects`) — un groupe d'objets implicite est créé par
    unité ;
  * la hiérarchie des unités s'exprime **uniquement par imbrication** (`ArchiveUnits` récursif) ;
  * le partage d'un même groupe d'objets entre plusieurs unités n'est pas supporté.
* **Clés en PascalCase** reprenant les noms des éléments SEDA XML.
* **Éléments multivalués → tableaux JSON** avec un nom au pluriel (`ArchiveUnits`, `Tags`,
  `Keywords`, `BinaryDataObjects`, `Writers`, …).
* **Attributs XML → propriétés JSON** : `<MessageDigest algorithm="…">` devient l'objet
  `{"Algorithm": "…", "Value": "…"}`.
* **Appariement explicite des règles** : la juxtaposition positionnelle XML
  `<Rule>`/`<StartDate>` devient un tableau d'objets `"Rules": [{"Rule": "…", "StartDate": "…"}]`.
* **Ordre des clés imposé** : il permet un parsing en streaming en une seule passe.
* **Éléments étendus** : toute clé non définie par le standard est acceptée **uniquement dans
  `Content`**.
* **Dates** au format ISO-8601 : `yyyy-MM-dd` pour les dates, `yyyy-MM-dd'T'HH:mm:ss` pour les
  dates-heures.

## Structure du SIP

```text
SIP.zip
├── manifest.json     # manifeste SEDA JSON décrivant chaque unité d'archive
└── content/          # objets binaires référencés par le manifeste
    ├── 1_fichier.pdf
    └── ...
```

Le manifeste `manifest.json` est placé à la racine du zip. Les objets binaires sont placés dans le
dossier `content/` (en minuscules, contrairement au dossier `Content/` du SEDA XML). SipG nomme
les entrées `content/<n>_<nom_de_fichier_assaini>` où `<n>` est un compteur garantissant
l'unicité.

## Ordre des clés

Le standard impose l'ordre des clés (miroir de la séquence du XSD SEDA). Cet ordre est vérifié par
le parser en streaming au niveau de la racine et des unités d'archives. À l'intérieur des
autres objets, l'ordre indiqué dans ce document est l'ordre normatif de génération.

* **Racine** : `SedaJsonVersion` (obligatoirement en premier), `MessageIdentifier?`, `Comment?`,
  `ArchivalAgreement`, `ArchiveUnits?`, `ManagementMetadata?`, `ArchivalAgency`,
  `TransferringAgency?`.
* **Unité d'archive** : `Management?`, `Content`, `BinaryDataObjects?`, `PhysicalDataObjects?`,
  `ArchiveUnits?`.

## Référence des clés

Les tableaux ci-dessous listent les clés dans l'ordre normatif. La cardinalité `0..1` indique une
clé optionnelle, `1` une clé obligatoire. La colonne « SEDA XML » indique l'élément XML correspondant avec une astérix
 lorsque le nom diffère.

### Racine du manifeste

| Clé | Type | Card. | Longueur max | SEDA XML |
|---|---|---|---|---|
| `SedaJsonVersion` | chaîne (constante `"2.0"`) | 1 | — | — (spécifique JSON) |
| `MessageIdentifier` | chaîne | 0..1 | 1024 | `MessageIdentifier` |
| `Comment` | chaîne | 0..1 | 5000 | `Comment` |
| `ArchivalAgreement` | chaîne | 1 | 512 | `ArchivalAgreement` |
| `ArchiveUnits` | tableau d'unités d'archives | 0..1 | — | `DescriptiveMetadata/ArchiveUnit*` |
| `ManagementMetadata` | objet | 0..1 | — | `ManagementMetadata` |
| `ArchivalAgency` | agence | 1 | — | `ArchivalAgency` |
| `TransferringAgency` | agence | 0..1 | — | `TransferringAgency` |

Si le `MessageIdentifier` n'est pas défini dans le modèle, SipG génère automatiquement un
identifiant aléatoire (comme pour les générateurs XML). La date du message (`Date`) et les
`CodeListVersions` du SEDA XML n'ont pas d'équivalent JSON et sont ignorées.

### ManagementMetadata

| Clé | Type | Card. | Longueur max |
|---|---|---|---|
| `ArchivalProfile` | chaîne | 0..1 | 512 |
| `ServiceLevel` | chaîne | 0..1 | 512 |
| `AcquisitionInformation` | chaîne | 0..1 | 512 |
| `LegalStatus` | chaîne | 0..1 | 512 |
| `OriginatingAgencyIdentifier` | chaîne | 0..1 | 512 |
| `SubmissionAgencyIdentifier` | chaîne | 0..1 | 512 |

### Agence (`ArchivalAgency`, `TransferringAgency`, `OriginatingAgency`, `SubmissionAgency`)

| Clé | Type | Card. | Longueur max | SEDA XML |
|---|---|---|---|---|
| `Identifier` | chaîne | 1 | 512 | `Identifier` |
| `Name` | chaîne | 0..1 | 512 | `OrganizationDescriptiveMetadata/Name` |

Les éléments étendus des agences (`OrganizationDescriptiveMetadata` libre du XML) ne sont pas
représentables en JSON (voir [constructions non représentables](#constructions-non-représentables)).

### Unité d'archive

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `Management` | objet | 0..1 | `ArchiveUnit/Management` |
| `Content` | objet | 1 | `ArchiveUnit/Content` |
| `BinaryDataObjects` | tableau (max 1000) | 0..1 | `BinaryDataObject*` du groupe d'objets |
| `PhysicalDataObjects` | tableau (max 1) | 0..1 | `PhysicalDataObject` du groupe d'objets |
| `ArchiveUnits` | tableau d'unités | 0..1 | `ArchiveUnit*` imbriquées |

L'attribut `id` de l'unité XML n'a pas d'équivalent et est ignoré.

### Management

L'ordre des clés suit la séquence du XSD `ManagementType` :

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `StorageRule` | règle + `FinalAction?` (`Copy`, `RestrictAccess`, `Transfer`) | 0..1 | `StorageRule` |
| `AppraisalRule` | règle + `Duration?` + `FinalAction?` (`Keep`, `Destroy`) | 0..1 | `AppraisalRule` |
| `AccessRule` | règle | 0..1 | `AccessRule` |
| `DisseminationRule` | règle | 0..1 | `DisseminationRule` |
| `ReuseRule` | règle | 0..1 | `ReuseRule` |
| `ClassificationRule` | règle + propriétés de classification | 0..1 | `ClassificationRule` |
| `LogBook` | tableau d'événements (max 10000) | 0..1 | `LogBook/Event*` |
| `NeedAuthorization` | booléen | 0..1 | `NeedAuthorization` |
| `HoldRule` | règle de gel | 0..1 | `HoldRule` |
| `UpdateOperation` | objet | 0..1 | `UpdateOperation` |

### Règles de gestion

Toutes les règles partagent la même structure de base :

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `Rules` | tableau (max 100) de `{"Rule": chaîne, "StartDate": date?}` | 0..1 | couples `Rule`/`StartDate` |
| `PreventInheritance` | booléen | 0..1 | `PreventInheritance` |
| `PreventRuleNames` | tableau de chaînes | 0..1 | `RefNonRuleId*` |

Conformément au `xsd:choice` du SEDA XML, `PreventInheritance` et `PreventRuleNames` sont
mutuellement exclusifs.

`ClassificationRule` ajoute : `ClassificationLevel?`, `ClassificationOwner?`,
`ClassificationAudience?`, `ClassificationReassessingDate?` (date),
`NeedReassessingAuthorization?` (booléen).

Les éléments des `Rules` de `HoldRule` portent les propriétés suivantes (dans cet ordre) :

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `Rule` | chaîne | 1 | `Rule` |
| `StartDate` | date | 0..1 | `StartDate` |
| `HoldEndDate` | date | 0..1 | `HoldEndDate` |
| `HoldOwner` | chaîne | 0..1 | `HoldOwner` |
| `ReassessingDate` | date | 0..1 | `HoldReassessingDate` |
| `HoldReason` | chaîne (1024) | 0..1 | `HoldReason` |
| `PreventRearrangement` | booléen | 0..1 | `PreventRearrangement` |

Note : la clé `ReassessingDate` (et non `HoldReassessingDate`) est retenue par le standard JSON.

### UpdateOperation

Soit `{"SystemId": "…"}`, soit `{"MetadataName": "…", "MetadataValue": "…"}` (exclusif, comme
l'`ArchiveUnitIdentifierKey` du XML).

### Content

L'ordre des clés suit la séquence du XSD `DescriptiveMetadataContentType` :

| # | Clé | Type | Card. | Longueur max | SEDA XML |
|---|---|---|---|---|---|
| 1 | `DescriptionLevel` | chaîne | 0..1 | 512 | `DescriptionLevel` |
| 2 | `Title` | chaîne | 1 | 1024 | premier `Title` |
| 3 | `Titles` | tableau de textes | 0..1 | 1024/élément | autres `Title` |
| 4 | `FilePlanPositions` | tableau de chaînes | 0..1 | 512 | `FilePlanPosition*` |
| 5 | `SystemIds` | tableau de chaînes | 0..1 | 512 | `SystemId*` |
| 6 | `OriginatingSystemIds` | tableau de chaînes | 0..1 | 512 | `OriginatingSystemId*` |
| 7 | `ArchivalAgencyArchiveUnitIdentifiers` | tableau de chaînes | 0..1 | 512 | `ArchivalAgencyArchiveUnitIdentifier*` |
| 8 | `OriginatingAgencyArchiveUnitIdentifiers` | tableau de chaînes | 0..1 | 512 | `OriginatingAgencyArchiveUnitIdentifier*` |
| 9 | `TransferringAgencyArchiveUnitIdentifiers` | tableau de chaînes | 0..1 | 512 | `TransferringAgencyArchiveUnitIdentifier*` |
| 10 | `Description` | chaîne | 0..1 | 131072 | premier `Description` |
| 11 | `Descriptions` | tableau de textes | 0..1 | 131072/élément | autres `Description` |
| 12 | `CustodialHistoryItems` | tableau (chaîne ou `{"Value", "When"?}`) | 0..1 | 512 | `CustodialHistory/CustodialHistoryItem*` |
| 13 | `ArchiveUnitProfile` | chaîne | 0..1 | 512 | `ArchiveUnitProfile` (niveau unité en XML) |
| 14 | `Type` | chaîne | 0..1 | 512 | `Type` |
| 15 | `DocumentType` | chaîne | 0..1 | 512 | `DocumentType` |
| 16 | `Languages` | tableau de chaînes | 0..1 | 512 | `Language*` |
| 17 | `DescriptionLanguage` | chaîne | 0..1 | 512 | `DescriptionLanguage` |
| 18 | `Status` | chaîne | 0..1 | 512 | `Status` |
| 19 | `Version` | chaîne | 0..1 | 512 | `Version` |
| 20 | `Tags` | tableau de chaînes | 0..1 | 512 | `Tag*` |
| 21 | `Keywords` | tableau de `{"KeywordReference", "KeywordContent"}` (max 100) | 0..1 | 512/1024 | `Keyword*` |
| 22 | `OriginatingAgency` | agence | 0..1 | — | `OriginatingAgency` |
| 23 | `SubmissionAgency` | agence | 0..1 | — | `SubmissionAgency` |
| 24 | `Agents` | tableau d'agents | 0..1 | — | `Agent*` (SEDA 2.2+) |
| 25 | `AuthorizedAgents` | tableau d'agents | 0..1 | — | `AuthorizedAgent*` |
| 26 | `Writers` | tableau d'agents | 0..1 | — | `Writer*` |
| 27 | `Addressees` | tableau d'agents | 0..1 | — | `Addressee*` |
| 28 | `Recipients` | tableau d'agents | 0..1 | — | `Recipient*` |
| 29 | `Transmitters` | tableau d'agents | 0..1 | — | `Transmitter*` |
| 30 | `Senders` | tableau d'agents | 0..1 | — | `Sender*` |
| 31 | `Source` | chaîne | 0..1 | 512 | `Source` |
| 32 | `RelatedObjectReference` | objet | 0..1 | — | `RelatedObjectReference` |
| 33 | `CreatedDate` | date | 0..1 | — | `CreatedDate` |
| 34 | `TransactedDate` | date | 0..1 | — | `TransactedDate` |
| 35 | `AcquiredDate` | date | 0..1 | — | `AcquiredDate` |
| 36 | `SentDate` | date | 0..1 | — | `SentDate` |
| 37 | `ReceivedDate` | date | 0..1 | — | `ReceivedDate` |
| 38 | `RegisteredDate` | date | 0..1 | — | `RegisteredDate` |
| 39 | `StartDate` | date | 0..1 | — | `StartDate` |
| 40 | `EndDate` | date | 0..1 | — | `EndDate` |
| 41 | `Events` | tableau d'événements (max 10000) | 0..1 | — | `Event*` |
| 42 | `Signatures` | tableau de signatures (max 100) | 0..1 | — | `Signature*` (SEDA 2.1/2.2) |
| 43 | `SigningInformation` | objet | 0..1 | — | `SigningInformation` (SEDA 2.3) |
| 44 | `Gps` | objet | 0..1 | — | `Gps` |
| 45+ | clés étendues | libre | 0..n | — | éléments de l'extension d'ontologie |

### Événement (`LogBook`, `Events`)

| Clé | Type | Card. | Longueur max |
|---|---|---|---|
| `EventIdentifier` | chaîne | 0..1 | 512 |
| `EventTypeCode` | chaîne | 0..1 | 512 |
| `EventType` | chaîne | 0..1 | 512 |
| `EventDateTime` | date-heure | 0..1 | 64 |
| `EventDetail` | chaîne | 0..1 | 512 |
| `Outcome` | chaîne | 0..1 | 512 |
| `OutcomeDetail` | chaîne | 0..1 | 512 |
| `OutcomeDetailMessage` | chaîne | 0..1 | 1024 |
| `EventDetailData` | chaîne | 0..1 | 1024 |

### Agent (`Agents`, `AuthorizedAgents`, `Writers`, `Addressees`, `Recipients`, `Transmitters`, `Senders`)

L'ordre suit la séquence du XSD `AgentType` :

| Clé | Type | Card. | Longueur max |
|---|---|---|---|
| `FirstName` | chaîne | 0..1 | 512 |
| `BirthName` | chaîne | 0..1 | 512 |
| `FullName` | chaîne | 0..1 | 512 |
| `GivenName` | chaîne | 0..1 | 512 |
| `Gender` | chaîne | 0..1 | 512 |
| `BirthDate` | date | 0..1 | — |
| `BirthPlace` | lieu | 0..1 | — |
| `DeathDate` | date | 0..1 | — |
| `DeathPlace` | lieu | 0..1 | — |
| `Nationalities` | tableau de chaînes | 0..1 | 512 |
| `Corpname` | chaîne | 0..1 | 512 |
| `Identifiers` | tableau de chaînes | 0..1 | 512 |
| `Functions` | tableau de chaînes | 0..1 | 512 |
| `Activities` | tableau de chaînes | 0..1 | 512 |
| `Positions` | tableau de chaînes | 0..1 | 512 |
| `Roles` | tableau de chaînes | 0..1 | 512 |
| `Mandates` | tableau de chaînes | 0..1 | 512 |

Le **signataire** (`Signer`) ajoute `SigningTime?` (date-heure) en dernière position et le
**valideur** (`Validator`) ajoute `ValidationTime?` (date-heure).

### Lieu (`BirthPlace`, `DeathPlace`)

`Geogname?`, `Address?`, `PostalCode?`, `City?`, `Region?`, `Country?` (chaînes, 512).

### RelatedObjectReference

Les relations sont regroupées par type (ordre du XSD) : `IsVersionOfs?`, `Replaces?`, `Requires?`,
`IsPartOfs?`, `References?`. Chaque tableau contient des objets à une seule clé parmi :

```json
{ "RepositoryArchiveUnitPID": "…" }
{ "RepositoryObjectPID": "…" }
{ "ExternalReference": "…" }
```

Les relations internes au paquet (`ArchiveUnitRefId`, `DataObjectReference`) ne sont pas
représentables.

### Signature (SEDA 2.1/2.2)

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `Signers` | tableau de signataires (max 100) | 0..1 | `Signer*` |
| `Validator` | valideur | 0..1 | `Validator` |
| `SignedObjectDigest` | `{"Algorithm", "Value"?}` | 1 | `ReferencedObject/SignedObjectDigest` |

Le `SignedObjectId` du XML est implicite : l'objet signé correspond aux objets de données déclarés
dans l'unité. Une signature dans une unité sans objet de données provoque une erreur.

### SigningInformation (SEDA 2.3)

L'ordre suit la séquence du XSD `SigningInformationType` :

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `SigningRoles` | tableau d'énumérés `SignedDocument`, `Timestamp`, `Signature`, `AdditionalProof` | 0..1 | `SigningRole*` |
| `DetachedSigningRoles` | tableau d'énumérés `Timestamp`, `Signature`, `AdditionalProof` | 0..1 | `DetachedSigningRole*` |
| `SignedDocumentReferenceIds` | tableau de chaînes | 0..1 | `SignedDocumentReferenceId*` |
| `SignatureDescriptions` | tableau de `{"Signer"?, "Validator"?, "SigningType"?}` | 0..1 | `SignatureDescription*` |
| `TimestampingInformations` | tableau de `{"TimeStamp"?, "AdditionalTimestampingInformation"?}` | 0..1 | `TimestampingInformation*` |
| `AdditionalProofInformation` | tableau de chaînes | 0..1 | `AdditionalProof/AdditionalProofInformation*` |

### Gps

`GpsVersionID?` (chaîne), `GpsAltitude?` (entier), `GpsAltitudeRef?`, `GpsLatitude?`,
`GpsLatitudeRef?`, `GpsLongitude?`, `GpsLongitudeRef?`, `GpsDateStamp?` (chaînes).

### Objet de données binaire (`BinaryDataObjects`)

Les objets sont générés dans l'ordre : BinaryMaster, Dissemination, Thumbnail, TextContent.

| Clé | Type | Card. | Longueur max | SEDA XML |
|---|---|---|---|---|
| `DataObjectVersion` | chaîne | 0..1 | 512 | `DataObjectVersion` |
| `Uri` | chaîne | 1 | 1024 | `Uri` |
| `MessageDigest` | `{"Algorithm" (1), "Value" (1)}` | 1 | 64/1024 | `MessageDigest` + attribut `algorithm` |
| `Size` | entier | 1 | — | `Size` |
| `FormatIdentification` | `{"FormatId"?, "FormatLitteral"?, "FormatName"?, "MimeType"?, "Encoding"?}` | 0..1 | 512 | `FormatIdentification` |
| `FileInfo` | objet | 0..1 | — | `FileInfo` |

`FileInfo` : `Filename`, `CreatingApplicationName?`, `CreatingApplicationVersion?`, `CreatingOs?`,
`CreatingOsVersion?`, `DateCreatedByApplication?` (date-heure), `LastModified?` (date-heure).

Comme pour les générateurs XML, l'empreinte (SHA-512 par défaut) et la taille sont calculées
automatiquement et le format est identifié via la librairie Droid lorsqu'il n'est pas fourni.

### Objet de données physique (`PhysicalDataObjects`)

| Clé | Type | Card. | SEDA XML |
|---|---|---|---|
| `DataObjectVersion` | chaîne | 0..1 | `DataObjectVersion` |
| `PhysicalId` | chaîne | 1 | `PhysicalId` |
| `Measure` | nombre | 0..1 | `PhysicalDimensions` |

## Textes multilingues

Le SEDA XML autorise plusieurs `Title` et `Description` avec un attribut `xml:lang`. Le standard
JSON impose une clé `Title` de type chaîne (resp. `Description`) et reporte les autres textes dans
le tableau pluriel `Titles` (resp. `Descriptions`) :

* `Title` reçoit le message du **premier texte sans langue** ; à défaut, celui du premier texte ;
* les autres textes sont placés dans `Titles` : objets `{"Lang": "…", "Value": "…"}` si une langue
  est définie, chaînes sinon.

```json
{
  "Title": "Bonjour",
  "Titles": [ { "Lang": "en", "Value": "Hello" } ]
}
```

## Éléments étendus

Toute clé non définie par le standard est acceptée **uniquement dans `Content`** et doit être
placée **après** les clés standard. Les règles de transposition des éléments étendus
(`fr.xelians.sipg.model.Element` ou fragments XML bruts) sont les suivantes :

* un élément feuille sans attribut devient une **chaîne** ;
* un élément avec enfants devient un **objet** ;
* les **attributs** deviennent des propriétés de l'objet et la valeur texte est placée sous la clé
  `"Value"` ;
* les noms répétés sont fusionnés en **tableau**.

```json
{
  "Direction": "Nord",
  "Employe": { "Nom": "Durand", "Prenoms": [ "Paul", "Henri" ] },
  "MonTag": { "attr1": "val1", "Value": "MaValeur" }
}
```

Une collision entre un attribut et un élément enfant de même nom provoque une erreur en mode
strict (l'attribut est ignoré avec un avertissement en mode non strict).

## Constructions non représentables

Les constructions suivantes du modèle SipG n'ont pas d'équivalent dans le standard SEDA JSON. En
mode **strict** (par défaut), leur présence provoque une `SipException` ; en mode **non strict**,
elles sont ignorées avec un avertissement :

| Construction | Raison |
|---|---|
| Références entre unités (`ArchiveUnitRef`) | pas de mécanisme de référence en JSON |
| Relations internes `ArchiveUnitRef`/`DataObjectRef` dans `RelatedObjectReference` | idem |
| Signature au niveau du message (`ArchiveTransfer.setSignature`) | hors périmètre du manifeste JSON |
| `DataObjectSystemId` (déprécié) | non repris par le standard |
| Éléments étendus des agences (`Agency.addElement`) | le schéma des agences est fermé |
| `ArchiveDeliveryRequestReply` (DIP) | le standard JSON ne définit que le transfert |

Sont par ailleurs **toujours ignorés** (trace de debug) : la date du message
(`ArchiveTransfer.setDate`), les `CodeListVersions`, l'identifiant d'unité (`ArchiveUnit.setId`)
et le `SignatureStatus`.

## Contraintes et limites

| Contrainte | Valeur |
|---|---|
| Taille maximale du manifeste lors de la validation | 128 Mio (configurable via `maxManifestSize`) |
| Profondeur maximale d'imbrication des unités | 50 |
| Profondeur maximale d'imbrication JSON | 200 |
| Longueur maximale d'une clé | 1024 |
| Longueur maximale d'une chaîne | 200 000 |
| Clés dupliquées | rejetées |
| Longueur par défaut des chaînes | 512 (sauf indication contraire) |
| Nombre maximal d'objets binaires par unité | 1000 |
| Nombre maximal d'objets physiques par unité | 1 |

La validation par schéma JSON matérialise le manifeste en mémoire (contrairement à la validation
XSD qui opère en streaming), d'où la limite de taille du manifeste. La génération n'est pas
concernée par cette limite.

## Exemple complet

```json
{
  "SedaJsonVersion": "2.0",
  "MessageIdentifier": "MSG-2026-0001",
  "Comment": "Versement des dossiers RH 2025",
  "ArchivalAgreement": "IC-000001",
  "ArchiveUnits": [
    {
      "Management": {
        "AppraisalRule": {
          "Rules": [ { "Rule": "APP-00001", "StartDate": "2025-01-01" } ],
          "FinalAction": "Destroy"
        },
        "AccessRule": { "Rules": [ { "Rule": "ACC-00002" } ] }
      },
      "Content": {
        "DescriptionLevel": "Item",
        "Title": "Dossier individuel — DURAND Paul",
        "Description": "Dossier de carrière",
        "CustodialHistoryItems": [ "Transféré du service RH" ],
        "DocumentType": "DossierRH",
        "Tags": [ "rh", "carriere" ],
        "Keywords": [ { "KeywordReference": "Matricule", "KeywordContent": "00123" } ],
        "StartDate": "2010-03-01",
        "EndDate": "2025-06-30",
        "Direction": "Nord",
        "Employe": { "Nom": "Durand", "Prenoms": [ "Paul", "Henri" ] }
      },
      "BinaryDataObjects": [
        {
          "DataObjectVersion": "BinaryMaster_1",
          "Uri": "content/1_dossier_durand.pdf",
          "MessageDigest": { "Algorithm": "SHA-512", "Value": "9f2c…" },
          "Size": 482133,
          "FormatIdentification": { "FormatId": "fmt/18", "MimeType": "application/pdf" },
          "FileInfo": { "Filename": "dossier_durand.pdf", "LastModified": "2025-11-02T10:15:30" }
        }
      ],
      "PhysicalDataObjects": [
        { "DataObjectVersion": "PhysicalMaster_1", "PhysicalId": "BOITE-42", "Measure": 1.5 }
      ],
      "ArchiveUnits": [
        { "Content": { "DescriptionLevel": "Item", "Title": "Pièce annexe" } }
      ]
    }
  ],
  "ManagementMetadata": {
    "ArchivalProfile": "PR-000012",
    "OriginatingAgencyIdentifier": "AG-000001"
  },
  "ArchivalAgency": { "Identifier": "AG-000001", "Name": "Service des archives" },
  "TransferringAgency": { "Identifier": "AG-000002" }
}
```

Dans cet exemple, `Direction` et `Employe` sont des éléments étendus : ils ne font pas partie du
standard et sont acceptés uniquement dans `Content`.

## Validation

La validation d'un paquet zip (`SedaJsonService.validate(Path)`) opère en trois temps :

1. la **validation par schéma** : le manifeste est validé contre le schéma JSON embarqué
   (structure, clés autorisées, types, longueurs maximales) ;
2. le **parsing en streaming** : l'ordre des clés imposé par le standard est vérifié et les objets
   binaires sont extraits ;
3. la **vérification des binaires** : existence, emplacement dans `content/`, taille et empreinte.

Chaque étape est notifiée au callback de progression (`ProgressListener<SedaJsonStep>`) :

| Étape | Description |
|---|---|
| `START` | début de la validation |
| `ARCHIVE_EXIST` | l'archive existe |
| `ARCHIVE_READABLE` | l'archive est lisible |
| `ARCHIVE_UNZIP` | l'archive est un zip valide |
| `MANIFEST_EXIST` | le manifeste `manifest.json` existe |
| `MANIFEST_SIZE` | la taille du manifeste est inférieure à la limite |
| `MANIFEST_SCHEMA` | le manifeste est conforme au schéma JSON du standard |
| `MANIFEST_PARSE` | l'ordre des clés est conforme et les objets binaires sont extraits |
| `BINARY_EXIST` | l'objet binaire existe dans le zip |
| `BINARY_FOLDER` | l'objet binaire est dans le dossier `content/` |
| `BINARY_SIZE` | la taille de l'objet binaire est conforme |
| `BINARY_DIGEST` | l'empreinte de l'objet binaire est conforme |
| `COMPLETE` | fin de la validation |

Un chemin se terminant par `.json` valide le manifeste seul (taille, schéma, ordre des clés), sans
vérification des binaires.

## Utilisation de l'API

```java
// Génération d'un SIP SEDA JSON
ArchiveTransfer archiveTransfer = new ArchiveTransfer();
archiveTransfer.setArchivalAgreement("IC-000001");
archiveTransfer.setArchivalAgency("AG-000001", "Service des archives");

ArchiveUnit unit = new ArchiveUnit();
unit.addTitle("Dossier individuel — DURAND Paul");
unit.setBinaryPath(Path.of("dossier_durand.pdf"));
archiveTransfer.addArchiveUnit(unit);

SedaJsonService sedaJsonService = SedaJsonService.getInstance();
sedaJsonService.write(archiveTransfer, Path.of("sip.zip"));

// Validation complète avec callback
SedaJsonConfig config = SedaJsonConfigBuilder.builder().format(true).build();
sedaJsonService.validate(Path.of("sip.zip"), config, event -> System.out.println(event));
```

La configuration `SedaJsonConfig` (créée via `SedaJsonConfigBuilder`) permet de contrôler :
la validation par schéma lors de la génération (`validate`), le formatage du manifeste (`format`,
`indent`), le nombre de threads (`thread`), le mode strict (`strict`), les vérifications des
binaires (`checkBinary`, `checkSize`, `checkDigest`), la génération en mémoire (`useMemory`),
l'identification des formats (`identifyFileFormat`) et la taille maximale du manifeste lors de la
validation (`maxManifestSize`).
