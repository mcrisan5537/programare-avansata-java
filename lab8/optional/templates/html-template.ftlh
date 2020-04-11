<!DOCTYPE html>

<html>

    <head>
      <title>Chart.</title>
      <style>
        * {
            text-align: center;
        }

        body {
            width: 80%;
            margin: 0 auto;
        }

        h3,
        h4 {
            font-weight: normal;
        }
      </style>
    </head>

    <body>

      <h1>
          THIS WEEK'S CHART
      </h1>

      <p>
        <#list chartEntries as entry>
          <h2>Entry #${entry.getPosition()}</h2>
          <h3>Artist: ${entry.getArtist().getName()}, from ${entry.getArtist().getCountry()}</h3>
          <h4>Album: ${entry.getAlbum().getName()} (${entry.getAlbum().getReleaseYearString()})</h4>
        </#list>
      </p>

</html>