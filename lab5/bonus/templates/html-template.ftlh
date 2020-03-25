<!DOCTYPE html>

<html>

    <head>
      <title>Catalog.</title>
    </head>

    <body>

      <h1>
        <pre>
          Catalog name : ${name}
          Catalog path : ${path}
          Catalog documents :
        </pre>
      </h1>

      <pre>
        <#list documents as document>
          ID = ${document.id}
          Name = ${document.name}
          Location = ${document.location}
          Tags
          <#list document.tags as tagname, tagval>
            ${tagname} = ${tagval}
          </#list>

        </#list>
      </pre>

</html>