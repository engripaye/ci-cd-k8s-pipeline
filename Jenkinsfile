pipeline {
  agent any

  environment {
    REGISTRY = "myregistry" // change to your registry
    KUBE_CONFIG_CRED = "kubeconfig-cred-id" // Jenkins credential (if used)
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Test') {
      parallel {
        stage('Python Build & Test') {
          steps {
            sh '''
              cd python-service
              python -m pip install --upgrade pip
              pip install -r requirements.txt
              pytest -q
            '''
          }
        }
        stage('Java Build & Test') {
          steps {
            sh '''
              cd java-service
              mvn -B clean package -DskipTests=false
            '''
          }
        }
      }
    }

    stage('Docker Build') {
      steps {
        sh '''
          docker build -t ${REGISTRY}/python-service:latest ./python-service
          docker build -t ${REGISTRY}/java-service:latest ./java-service
        '''
      }
    }

    stage('Push Images') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-reg-cred', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
          sh '''
            echo "$PASS" | docker login -u "$USER" --password-stdin
            docker push ${REGISTRY}/python-service:latest
            docker push ${REGISTRY}/java-service:latest
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        // Option A: use kubectl configured on Jenkins agent
        sh 'kubectl apply -f k8s/'

        // Option B: if using kubeconfig credential, write it and use it
        // writeFile file: 'kubeconfig', text: credentials('kubeconfig-cred-id')
        // sh 'KUBECONFIG=kubeconfig kubectl apply -f k8s/'
      }
    }
  }
}
