#!/usr/bin/groovy

def call(project, app){
    projectSet(project)
    sh ''' 
    cd ${app}
    pwd
    oc new-build -n ${project} --binary --name=${app} -l app=${app} || echo 'Build exists'
    oc start-build ${app} -n ${project} --from-dir=. --follow
    
    ''' 
    deployApp(project, app, 'latest')
}
