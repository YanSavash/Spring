# Select the image to use
FROM node

WORKDIR /usr/src/app
## Install dependencies in the root of the Container
COPY frontend/package*.json ./

COPY frontend/. .

RUN npm install

RUN npm build

EXPOSE 3030

CMD ["npm", "run", "start"]

