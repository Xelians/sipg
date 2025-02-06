
1. Before generating the JAXB classes

 * "Unwrapped" the HoldRuleGroup in the HoldRuleType in the seda-vitam-management.xsd

2. Generate the JAXB classes with maven

For SEDA 2.3:
> mvn clean generate-sources -Pseda23
