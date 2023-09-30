 # TomcatVirtualThreads

 Run Tomcat with StandardVirtualThreadExecutor enabled

 # Requirements
* Rancher Desktop or https://github.com/containerd/nerdctl

 # Building and running
 
 Build and run the container

 ~~~
 nerdctl build -f Dockerfile . -t tomcat-11-jdk21
 nerdctl run -d -p 8080:8080 tomcat-11-jdk21
 ~~~

Run a Tomcat Servlet 

 ~~~
curl http://localhost:8080/Benchmark/get-thread
 ~~~