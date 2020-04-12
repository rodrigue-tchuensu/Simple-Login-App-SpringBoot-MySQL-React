#### Stage 1: Build the react application

FROM node:12.4.0-alpine as build

MAINTAINER Rodrigue Tchuensu P. <pouopse@yahoo.fr>

# Configure the main working directory inside the docker image. 
# This is the base directory used in any further RUN, COPY, and ENTRYPOINT 
# commands.

WORKDIR /app

# Copy the package.json as well as the package-lock.json and install 
# the dependencies. This is a separate step so the dependencies 
# will be cached unless changes to one of those two files 
# are made.

COPY sources/package.json ./
COPY sources/package-lock.json ./
RUN npm install

# Copy the main application
COPY sources ./


# Arguments
ARG REACT_APP_API_BASE_URL

ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}


# Build the application
RUN npm run build



#### Stage 2: Serve the React application from Nginx 
FROM nginx:1.17.0-alpine


RUN rm  /usr/share/nginx/html/*

COPY conf/default.conf /etc/nginx/conf.d/

COPY --from=build /app/build/ /usr/share/nginx/html



EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]