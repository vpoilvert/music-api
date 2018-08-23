# music-api

## Description

Mucis management api that uses Kotlin and Spring Boot 2 framework.

Execute this command in a terminal:

```bash
./gradlew bootRun
```

Or for Window :

```
gradlew.bat bootRun
```

L'api est accessible à l'url `http://localhost:8080`.

## Artist API

```bash
# Lister les artistes
GET /artist?size=5&page=1
# Afficher un artiste
GET /artist/{id}
# Modifier un artiste
POST /artist/{id}
# Supprimer un artiste
DELETE /artist/{id}
```

JSON d'un artiste :
```json
{
   "id":100,
   "activeYearBegin":2004,
   "activeYearEnd":null,
   "imageFile":"https://freemusicarchive.org/file/images/artists/Chandeliers_-_2009113014608987.png",
   "location":"Chicago, IL",
   "name":"Chandeliers",
   "url":"http://freemusicarchive.org/music/Chandeliers/",
   "tags":[

   ]
}
```

### Album API

```bash
# Lister les albums
GET /album?size=5&page=1
# Afficher un album
GET /album/{id}
# Modifier un album
POST /artist/{id}
# Supprimer un album
DELETE /album/{id}
```

JSON d'un album :
```json
{
   "id":1,
   "releaseDate":null,
   "imageFile":"",
   "producer":"https://freemusicarchive.org/file/images/artists/AWOL_-_2009113014453413.jpg",
   "title":"New Jersey",
   "tracks":"AWOL",
   "url":"http://freemusicarchive.org/music/AWOL/",
   "tags":[

   ]
}
```