docker stop "$(docker ps -q --filter name=com-twitter-app )"
docker build -f Dockerfile -t c1/com-twitter-app .
docker run --rm -p 8072:8070 -d --env-file ./.env --name com-twitter-app c1/com-twitter-app
docker ps

#docker exec -it "$(docker ps -q --filter name=affiniti_dms_dlg_mife_be_1 )" /bin/bash

#tail -f /logs/affiniti-dms-dlg-error-logger.log
#tail -f /logs/affiniti-dms-dlg-trace-logger.log
#tail -f /logs/affiniti-dms-dlg-spring-logger.log