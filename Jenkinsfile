pipeline {
    agent any

    stages {
        // Jenkins автоматически делает Checkout из Git (Pipeline script from SCM)
        // перед запуском этих этапов.

        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }

    post {
        always {
            // Публикуем результаты JUnit-тестов в интерфейсе Jenkins
            // (Maven сохраняет их в target/surefire-reports)
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
        success {
            echo 'Pipeline завершен успешно: Build -> Test -> Package'
        }
        failure {
            echo 'Pipeline завершился с ошибкой. Смотри Console Output.'
        }
    }
}
