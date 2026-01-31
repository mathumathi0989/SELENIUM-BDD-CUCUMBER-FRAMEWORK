pipeline {
    agent any

    environment {
        TEAM_NAME = 'VM_Her_Balance_Trial'
        TEAM_EMAILS = 'vijayashree80sv@gmail.com, mathumathi.b@gmail.com'
        MAX_RETRIES = 3
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Main Tests') {
            steps {
                echo "Running main TestRunner"
                sh 'mvn clean test -Dtest=testRunner.TestRunner'
            }
        }

        stage('Retry Failed Scenarios') 		{
		            steps {
		                script {
		                    int maxRetries = env.MAX_RETRIES.toInteger()
		                    for (int retryCount = 1; retryCount <= maxRetries; retryCount++) {
		                        if (fileExists('target/failed_scenarios.txt') && readFile('target/failed_scenarios.txt').trim()) {
		                            echo "Retry attempt #${retryCount} for failed scenarios..."
		                            sh "mvn test -Dtest=testRunner.RetryFailedScenariosRunner"
		                        } else {
		                            echo "No failed scenarios left. Exiting retries."
		                            break
		                        }
		                    }
		                }
		            }
		        }
		    }

    post {
        always {
            echo "Archiving reports..."
            archiveArtifacts artifacts: 'target/cucumber*.html, target/cucumber*.json', allowEmptyArchive: true
        }

        failure {
            echo "Sending email notification to the team..."
            mail to: "${TEAM_EMAILS}",
                 subject: "Build Failed:",
                 body: """Hello Team ${TEAM_NAME},

								The Jenkins build has failed.
							"""
        }

        success {
			echo " BUILD SUCCESSFUL - ${TEAM_NAME}"
			           echo "All reports generated successfully!"
			           echo "Check Jenkins for:"
			           echo "1. Cucumber HTML Report"
			           echo "2. ExtentReport"
			           echo "3. Allure Report"
			           // Email notification for success
			           emailext(
			                subject: ":white_check_mark: SUCCESS: Team_VM",
			               body: """<h2>:white_check_mark: Her Balance Test Build Successful</h2><p><strong>Status:</strong> SUCCESS ✓</p><h3>:bar_chart: Report Links:</h3><ul><li><a href="cucumber-html-reports/">Cucumber HTML Report</a></li><li><a href="ExtentReport/">ExtentReport</a></li><li><a href="allure/">Allure Report</a></li></ul><h3>:busts_in_silhouette: Team Members Notified:</h3><p>${env.TEAM_EMAILS}</p><hr><p><small>This is an automated message from Jenkins CI/CD Pipeline</small></p>""",
			               to: "${TEAM_EMAILS}",
			               attachLog: false,
			               compressLog: false
			           )
        }
    }
}
