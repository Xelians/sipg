# SipG

## Présentation
* SipG est une bibliothèque pour générer et valider des archives au format SEDA v2.1.

## Fonctionnalités

La librairie SipG offre les fonctionnalités suivantes : 

* Génération d’un SIP conforme au format SEDA v2.1
* Suivi précis des étapes de validation par callback 
* Validation optionnelle du fichier de description de l'archive par un profil RNG 
* Application automatique de valeurs par défaut raisonnables
* Support des tags et des fragments XML étendus
* Identification des formats des objets binaires (intégration de la librairie Droid)
* Calcul automatique des empreintes des objets binaires
* Support des archives numériques et physiques
* Validation complète d'une archive existante au format SEDA v2.1
* Sérialisation/désérialisation du fichier de description au format JSON
* Capacité à générer des archives extrêmement volumineuses (plus de 100 000 objets)
* Support du multi-threads lors de la génération de l'archive
* Documentation Javadoc exhaustive 

## Format SEDA v2.1

Le standard d'échange de données pour l'archivage SEDA modélise les différentes transactions qui peuvent avoir 
lieu entre des acteurs dans le cadre de l'archivage de données. 

La documentation complète du SEDA v2.1 est disponible sur le site de [France Archive](https://redirect.francearchives.fr/seda/).

## Architecture fonctionnelle
 
La librairie SipG a pour objectif de faciliter la création et la validation d'archives. Dans un premier temps, l'application de traitement des archives récupère et transforme les données métiers, puis grâce à SipG, génère et valide les SIP (Submission Information Package) dans un format compatible avec celui du système d'archivage.

![Schéma d'architecture](./doc/assets/architecture.png)

## Exemples 

Le répertoire contenant les tests d'intégration fournit de nombreux exemples d'utilisation de la librairie.

### Création d’une archive SEDA avec SipG

```
ArchiveUnit unit = new ArchiveUnit();                    // Instancie l’unité d’archive
unit.setBinaryPath(Paths.get("dummy.pdf"));              // Spécifie le fichier de données
unit.addTitle("MyTitle");                                // Spécifie un titre
unit.addTag("This is a tag");                            // Spécifie un mot clé

ArchiveTransfer archiveTransfer = new ArchiveTransfer();          // Instancie l’archive à transférer
archiveTransfer.setArchivalAgreement("My Archival Agreement");    // Spécifie le contrat 
archiveTransfer.setArchivalAgency("AG001", "");                   // Spécifie le service d’archive
archiveTransfer.setTransferringAgency("AG002", "");               // Spécifie le service versant
archiveTransfer.addArchiveUnit(unit);                             // Ajoute l’unité à l’archive

Sedav2Service.getInstance().write(archiveTransfer, Paths.get("seda.zip"));  // Génère le SIP
```

### Création d’une archive au format SEDA à partir d'un fichier CSV

Fichier CSV :
```
dummy.pdf;MyTitle1;tag1
dummy.pdf;MyTitle2;tag2
dummy.pdf;MyTitle3;tag3
dummy.pdf;MyTitle4;tag4
dummy.pdf;MyTitle5;tag5
```
Code :
```
ArchiveTransfer archiveTransfer = new ArchiveTransfer();                   // Instancie l’archive
archiveTransfer.setArchivalAgreement("My Agreement");
archiveTransfer.setArchivalAgency("AG001", "My Archive Agency");
archiveTransfer.setTransferringAgency("AG002", "My Transfer Agency");

try (BufferedReader reader = Files.newBufferedReader(csvPath)) {           // Ouvre le fichier CSV
   CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').parse(reader);  // Instancie le parser CSV
   for (CSVRecord record : parser) {                                       // Parcours le fichier CSV
       ArchiveUnit unit = new ArchiveUnit();                               // Une archive peut contenir   
       unit.setBinaryPath(Paths.get(record.get(0)));                       // plusieurs unités d'archives 
       unit.addTitle(record.get(1));
       unit.addTag(record.get(2));
       archiveTransfer.addArchiveUnit(unit);                               // Ajout de l'unité à l'archive
   }
}
Sedav2Service.getInstance().write(archiveTransfer, Paths.get("seda.zip"));  // Génère le SIP
```

### Validation d'un fichier XML au format SEDA

```
Path path = Paths.get("seda_small.xml");               // Le fichier XML à valider
Sedav2Service.getInstance().validate(path);            // Validation du fichier 
```

### Validation d'un fichier XML selon un profil RNG

```
Path path = Paths.get("seda_small.xml");               // Le fichier XML à valider
Path rngPath = Paths.get("seda_small.rng");            // Le profil RNG
Validator rng = Validators.getRngValidator(rngPath);   // Création d'un validator RNG
Validators.validate(path, rng);                        // Validation du fichier avec le validateur
```

### Validation complète (structure & xml) avec callback d'une archive au format SEDA

Code :
```
Path input = Paths.get("seda.zip");                    // Archive à valider
Sedav2Config cnf = Sedav2Config.DEFAULT;               // Configuration pour contrôler la validation
Validator val = null;                                  // Validation RNG optionnelle

Sedav2Service.getInstance().validate(input, val, cnf, event -> LOGGER.info(event.toString()));
```

Résultat de la validation :
```
ProgressEvent{id=seda, status=SUCCESS, step=START, message=Archive: seda.zip}
ProgressEvent{id=seda, status=SUCCESS, step=ARCHIVE_EXIST, message=Archive exists}
ProgressEvent{id=seda, status=SUCCESS, step=ARCHIVE_READABLE, message=Archive is readable}
ProgressEvent{id=seda, status=SUCCESS, step=ARCHIVE_UNZIP, message=Archive is opened}
ProgressEvent{id=seda, status=SUCCESS, step=MANIFEST_EXIST, message=Manifest exists}
ProgressEvent{id=seda, status=SUCCESS, step=MANIFEST_SEDA, message=Manifest conforms to SEDA}
ProgressEvent{id=seda, status=SUCCESS, step=MANIFEST_PARSE, message=Manifest is parsed}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_EXIST, message=Binary object exists: Content/helloworld_1.pdf}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_FOLDER, message=Binary object folder is valid}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_SIZE, message=Binary object size is valid}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_DIGEST, message=Binary object digest is valid}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_EXIST, message=Binary object exists: Content/helloworld_2.pdf}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_FOLDER, message=Binary object folder is valid}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_SIZE, message=Binary object size is valid}
ProgressEvent{id=seda, status=SUCCESS, step=BINARY_DIGEST, message=Binary object digest is valid}
ProgressEvent{id=seda, status=SUCCESS, step=COMPLETE, message=Archive is valid}
```

### Désérialisation JSON

Fichier minisip.json :
```
{
   "codeListVersions": {},
   "archivalAgreement": "My Archival Agreement",
   "archivalAgency": {
       "identifier": "AG001"
   },
   "transferringAgency": {
       "identifier": "AG002"
   },
   "archiveUnits": [{
           "binaryPath": "file:///resources/dummy.pdf",
           "binaryVersion": "BinaryMaster_1",
           "digestAlgorithm": "SHA-512",
           "titles": [{ "message": "MyTitle" }],
           "descriptions": [{ "message": "This is a description" }]
       }]
}
```
Code : 
```
Path jsonPath = Paths.get("minisip.json");
ArchiveTransfer archiveTransfer = JsonService.getInstance().read(jsonPath);
```

L'objet archiveTransfer, issu de la désérialisation, peut ainsi être utilisé et modifié pour générer une nouvelle archive. 

## Contribuer au projet
1. Forker le projet Github
2. Créer sa propre branche (git checkout -b newFeature)
3. Commiter les modifications (git commit -am "nouvelle modification")
4. Pusher la branche (git push origin newFeature)
5. Créer une pull request

## Licences

La librairie SipG est publiée en Open Source sous [licence libre Apache v2](./doc/license/LICENCE.APACHE_V2).

La librairie [Droid](https://github.com/digital-preservation/droid) éditée par The British National Archive est disponible sur Github sous [licence BSD](./doc/license/LICENCE.DROID).
