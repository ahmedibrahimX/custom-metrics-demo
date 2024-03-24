# Custom Metrics Demo

> This is the companion Github repo of the following [medium article](https://medium.com/@a.ibrahim.abdellatif/from-chaos-to-clarity-monitoring-your-spring-boot-app-aa85bb500d4d) that I wrote on implementing custom metrics in Spring Boot including examples of some advanced techniques such as Aspect Oriented Programming (AOP), scraping the app's metrics using Prometheus and visualizing them using Grafana to create insightful dashboards.

### How to run

```
sudo docker-compose up --build -d
```

### How to use

- Use this curl to create orders
  ```
  curl --request POST \
  --url http://localhost:8080/orders \
  --header 'Content-Type: application/json' \
  --data '{
	"itemName": "item1"
  }'
  ```

- Access Grafana at `http://localhost:3000` and when you configure Prometheus data source in Grafana use `http://my-prometheus:9090`