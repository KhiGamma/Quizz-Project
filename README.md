# Quizz project

Ceci est notre application de quizz utilisant des microservices spring.

# Comment lancer

Lancer le service __server__ puis le __config server__ et finalement les autres services peu importe l'ordre.

Les services __game__ et __quizz__ peuvent être répliqués sur plusieurs ports.

## Via l'IDE

Ouvrez le projet via votre IDE et lancer le à l'aide de l'outil pour run.

Ajoutez les arguments nécessaires afin de modifier le port du service.

## Via ligne de commande

Faites la commande `mvn package` dans les dossiers de chaque service.

Une fois fait, lancez le service via `java -jar ./target/non-du-service_version.jar` depuis la racine du service en question.

Ajoutez le parametre `--server.port=<port>` après le `.jar` dans la commande precedente afin de lancer le service sur le port specifié.