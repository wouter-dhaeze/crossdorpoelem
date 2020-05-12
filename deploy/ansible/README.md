## Setup hosting

The ansible playbook(s) provision a host with the necessary tool to host the Crossdorp Website on a K3S-supported Kubernetes cluster.

### Prerequisites
* You have an ssh connection to the host you want to provision from your local machine
* You have a user with `root` or `sudo` privileges
* The host is connected to the internet (if local firewall is disabled)
* Following tools are installed on your local machine:
** Ansible (https://docs.ansible.com/ansible/latest/installation_guide/intro_installation.html)
** kubectl (https://kubernetes.io/docs/tasks/tools/install-kubectl/)
** k3sup (https://github.com/alexellis/k3sup)

### Provision VM
```
cd deploy/ansible
ansible-playbook website.yml -v --extra-vars '{"user": "root"}'-i production.yml
```

### k3s
The ansible script install k3s on the host. In order to get the `kubeconfig`, install k3sup on you local machine and run:
```
cd /tmp
k3sup install --ip 1.2.3.4 --user user --skip-install
```
Then check kube connectivity
```
export KUBECONFIG=/tmp/kubeconfig
kubectl get nodes
```