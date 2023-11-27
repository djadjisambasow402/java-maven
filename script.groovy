def buildJar() {
    echo "building the application..."
    powershell 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        powershell 'docker build -t domoda/awsapp:v1 .'
        powershell "echo $PASS | docker login -u $USER --password-stdin"
        powershell 'docker push domoda/awsapp:v1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
