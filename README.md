Core Concept Questions

**Why is Docker useful in building and deploying microservices for a real-world product (like an e-commerce or banking app)?**

Docker enables:

    Environment Consistency: Same runtime across dev, test, and production.

    Isolation: Services can run independently with their own dependencies.

    Portability: Easily move containers between machines or clouds.

    Faster CI/CD: Lightweight containers start quickly, speeding up pipelines.

    Scalability: Easy to replicate containers for load balancing.

In microservices architecture (like in banking/e-commerce apps), each service can be independently containerized, updated, scaled, and deployed without affecting others.

**What is the difference between a Docker image and a container in the context of scaling a web application?**

Docker Image	Blueprint for creating containers. Immutable and versionable. Think of it like a class in OOP.
Docker Container	A running instance of an image. Think of it like an object in OOP.

When scaling, you create multiple containers (instances) from a single image. For example, load balancers can distribute traffic across multiple containers spun from the same image.

**How does Kubernetes complement Docker when running a product at scale?**

Kubernetes helps manage containers at scale by providing:

    Orchestration: Automatically manages deployment, scaling, and operation of containers.

    Self-healing: Restarts failed containers, reschedules them, and replaces them when needed.

    Load balancing: Distributes traffic evenly across containers.

    Declarative Configuration: Describes desired state via YAML manifests.

    Auto-scaling: Increases/decreases pods based on CPU/memory metrics.


**Why do we set requests and limits for CPU/memory in production-grade products?**

    Requests specify the minimum guaranteed resources a container needs. Kubernetes uses requests to decide where to schedule a pod — it won’t place a pod on a node unless the node has at least that much available resource.

    Limits specify the maximum resources a container can use. If the container tries to use more, it may be throttled (CPU) or killed (memory).

Benefits:

    Fair resource sharing: Prevents a single container from starving others by hogging resources.

    Stability & reliability: Avoids resource exhaustion on nodes that could crash other workloads.

    Better scheduling: Kubernetes can efficiently pack pods on nodes based on requests, improving utilization.

    Cost control: Helps teams control cloud resource costs by capping resource usage.

**When would a product team apply node affinity in Kubernetes?**

Node affinity is used when you want to control where your pods run based on node characteristics.

Common scenarios:

    Hardware requirements: Pods need nodes with special hardware, e.g., GPU, SSDs, or high-memory machines.

    Compliance & security: Run certain workloads only on specific nodes that meet security policies or geographic locations.

    Performance optimization: Schedule latency-sensitive pods on nodes with faster network or CPU.

    Workload isolation: Separate critical workloads from others by restricting which nodes they run on.

    Cost optimization: Assign pods to cheaper nodes if performance is less critical.

**Why is Helm important for managing configuration across different environments (like dev, staging, prod)?**

Helm is like a recipe book for your app. In a real-world company, the same app runs in different places — like dev for testing, staging for review, and production for real users.

Each place needs slightly different settings — maybe different image tags, replica counts, or service types.

Instead of writing separate YAMLs again and again, Helm lets me write one flexible chart, and I just change a few values in a file called values.yaml for each environment.

So it saves time, avoids mistakes, and makes things consistent across all environments.

**How does Helm simplify deployment rollback during a production incident?**

Sometimes we release something new and it breaks in production. 

Helm keeps track of all the past deployments like a version history.

If something goes wrong, I can simply run:

    helm rollback my-app 1

And Helm will go back to the previous working version, just like undoing a change.

This is much easier than editing YAML files manually or trying to guess what changed.