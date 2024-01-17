# Download jxab-ri on https://repo1.maven.org/maven2/com/sun/xml/bind/jaxb-ri/

# Goto the directory where the xsd schema are located
# Edit, if necessary, the catalog to use the oasis XML format
# Create a java directory then execute xjc
../jaxb/bin/xjc.sh -catalog seda-vitam.cat -nv -d java  seda-vitam-main.xsd -b binding.xjb -extension
