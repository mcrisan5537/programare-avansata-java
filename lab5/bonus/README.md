# SHELL INTERACTION

java -jar lab5bonus.jar 
CatalogShell> load catalog.txt
CatalogShell> list

Document #1
id=java1
name=Java course1
location=https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf
locationIsFile=false
Tags=1
Type=Slides

Document #2
id=text1
name=fisier text
location=./test.txt
locationIsFile=true
Tags=2
Type=Text
Content=ASCII text

CatalogShell> view text1
CatalogShell> view java1
CatalogShell> report html                
CatalogShell> info html-report.html
X-Parsed-By=org.apache.tika.parser.DefaultParser
X-Parsed-By=org.apache.tika.parser.html.HtmlParser
dc:title=Catalog.
Content-Encoding=ISO-8859-1
title=Catalog.
Content-Type=text/html;
charset=ISO-8859-1
CatalogShell> info catalog.txt
X-Parsed-By=org.apache.tika.parser.DefaultParser
X-Parsed-By=org.apache.tika.parser.csv.TextAndCSVParser
Content-Encoding=ISO-8859-1
Content-Type=text/plain;
charset=ISO-8859-1
CatalogShell> info lab5bonus.jar
SAXException when parsing lab5bonus.jar.
X-Parsed-By=org.apache.tika.parser.DefaultParser
X-Parsed-By=org.apache.tika.parser.pkg.PackageParser
Content-Type=application/java-archive
CatalogShell> exit