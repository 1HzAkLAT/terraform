node("linux_standard||deploy_linux_standard")
{
    ws('/var/lib/jenkins/workspace/tesst') {
    // some block
    }
    stage('Initialization') {
        sh '''
        pwd
        whoami
        which node
        node -v
        '''
    }      
     stage('Clone sources') {
          checkout([$class: 'GitSCM',
          branches: [[name: '*/dev']],
          doGenerateSubmoduleConfigurations: false,
          extensions: [[$class: 'RelativeTargetDirectory',
          relativeTargetDir: 'different_directory']],
          submoduleCfg: [],
          userRemoteConfigs: [[url: 'https://github.com/1HzAkLAT/nodej-projects.git']]])
    }

    stage('Build') {
        // configuration
           sh '''
              npm install
            '''
    }

    stage('Test') {
        sh '''
        npm run test
        '''
    }

}`