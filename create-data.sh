#! /bin/sh

HOST="http://localhost:8080"
CONTEST="OSCUACS_2015"

# Contest
curl --request POST \
  --url $HOST/api/contests/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Oscuacs 2015"}'

# Shows

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows \
  --header 'content-type: application/json' \
  --data '{"value": "Spoiler"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows \
  --header 'content-type: application/json' \
  --data '{"value": "Falso 9"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows \
  --header 'content-type: application/json' \
  --data '{"value": "Alegría"}'

# People

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows/SPOILER/members \
  --header 'content-type: application/json' \
  --data '{"name": "Álex"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows/SPOILER/members \
  --header 'content-type: application/json' \
  --data '{"name": "Isa"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows/SPOILER/members \
  --header 'content-type: application/json' \
  --data '{"name": "Antonio"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows/ALEGRA/members \
  --header 'content-type: application/json' \
  --data '{"name": "Isa"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows/ALEGRA/members \
  --header 'content-type: application/json' \
  --data '{"name": "Tomi"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/shows/ALEGRA/members \
  --header 'content-type: application/json' \
  --data '{"name": "Mariano"}'

# Categories

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor producción"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor promoción"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor iniciativa comunitaria"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor técnico novel"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor técnico"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor locutora novel"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor locutor novel"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor locutor"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor locutora"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor programa divulgativo"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor programa social/politico"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor programa musical"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor programa novel"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/quick \
  --header 'content-type: application/json' \
  --data '{"value": "Mejor programa"}'

# Candidates

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_LOCUTOR/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "Isa"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_LOCUTOR/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "Tomi"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_LOCUTOR/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "Mariano"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_TCNICO/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "Mariano"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_TCNICO/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "Antonio"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_PROGRAMA/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "Spoiler"}'

curl --request POST \
  --url $HOST/api/contests/$CONTEST/categories/MEJOR_PROGRAMA/candidates \
  --header 'content-type: application/json' \
  --data '{"value": "ALEGRA"}'