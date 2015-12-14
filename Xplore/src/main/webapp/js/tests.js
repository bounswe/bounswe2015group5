/**
 * Test for isLoggedIn function.
 * @returns {undefined}
 */
function testIsLoggedIn() {
    userObj = {Email: "abbas@guclu.com", Name: "Abbas", Surname: "Guclu"};
    QUnit.test( "isLoggedIn test", function( assert ) {
        assert.ok( isLoggedIn(userObj) == true, "Passed!" );
    });
}