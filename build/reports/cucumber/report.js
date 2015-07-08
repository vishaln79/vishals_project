$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("createOrder.feature");
formatter.feature({
  "line": 1,
  "name": "Create Order",
  "description": "",
  "id": "create-order",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2068986394,
  "status": "passed"
});
formatter.scenario({
  "line": 2,
  "name": "Green path",
  "description": "",
  "id": "create-order;green-path",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "a valid order",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "the order is posted to the entry endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 5,
  "name": "a success message is returned",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "the order is placed in the vendor interface",
  "keyword": "And "
});
formatter.match({
  "location": "add_steps.groovy:30"
});
formatter.result({
  "duration": 192157411,
  "status": "passed"
});
formatter.match({
  "location": "add_steps.groovy:34"
});
formatter.result({
  "duration": 898680021,
  "status": "passed"
});
formatter.match({
  "location": "add_steps.groovy:50"
});
formatter.result({
  "duration": 6414155,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 73058598,
  "status": "passed"
});
formatter.before({
  "duration": 298782903,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Invalid input",
  "description": "",
  "id": "create-order;invalid-input",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "an invalid order",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "the order is posted to the entry endpoint",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "a validation error message is returned",
  "keyword": "Then "
});
formatter.match({
  "location": "add_steps.groovy:74"
});
formatter.result({
  "duration": 119311,
  "status": "passed"
});
formatter.match({
  "location": "add_steps.groovy:34"
});
formatter.result({
  "duration": 76132338,
  "status": "passed"
});
formatter.match({
  "location": "add_steps.groovy:100"
});
formatter.result({
  "duration": 9116450,
  "error_message": "java.lang.NullPointerException: Cannot get property \u0027status\u0027 on null object\n\tat add_steps$_run_closure6.doCall(add_steps.groovy:103)\n\tat ✽.Then a validation error message is returned(createOrder.feature:11)\n",
  "status": "failed"
});
formatter.after({
  "duration": 31240401,
  "status": "passed"
});
});