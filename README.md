use **-DTEST_ENV=qa** to set the test env <br/>
use **-Dthreads=4** to set thread count <br/>
use **-DCI=true** to enable retries <br/>
use **-DRESTASSURED_BASEURI** to override any property of yaml config file <br/>
use **-Dgroups=smoke** to run a specific group of tests <br/>
use **-DexcludeGroups=prod** to exclude a specific group of tests from a run <br/>
use **allure generate allure-results --clean -o allure-report** to generate allure report <br/>