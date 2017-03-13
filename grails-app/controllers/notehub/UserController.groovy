package notehub


import grails.rest.*
import grails.converters.*
import grails.plugin.springsecurity.annotation.Secured

/**
 * Controls the creation and retrieval of users
 * @author Cameron Nicolle
 */
@Secured(['ROLE_USER'])
class UserController extends RestfulController {
	static responseFormats = ['json']


    /**
     * Constructor for UserController
     */
    UserController() {
        super(User)
    }

    /**
     * Default get action for user
     * Accessed at /user/
     * @params id       Id of user
     * or
     * @params email    Email of user
     * @params password Password of user
     * @return      Renders user, 404 or 400
     */
    def index() {

        // id does not exist
        if (params.id == null){
            // check username and password
            // get params
            String email = params.email
            String password = params.password

            // params not provided
            if (email == null || password == null){
                render(status: 400)
                return
            }

            // find account
            def account = Account.findByEmailAndPassword(email, password)

            // account not found
            if (account == null){
                render(status: 404)
                return
            }

            // success
            this.renderUser(account.getUserId())
            return
        }


        try{
            this.renderUser(Long.parseLong(params.id.toString()))
        } catch (NumberFormatException e){
            render(status: 400)
            return
        }

    }

    /**
     * Show get action for user
     * Accessed at /user/id
     * @params id   Id of user
     * @return      Renders user, 404 or 400
     */
    def show() {

        // id does not exist
        if (params.id == null){
            render (status: 400)
            return
        }

        try{
            this.renderUser(Long.parseLong(params.id.toString()))
        } catch (NumberFormatException e){
            render(status: 400)
            return
        }
    }

    /**
     * Default POST action for user
     * Accessed at /user/
     * Provide JSON with name, email, password and picture (encoded in base64) fields
     * @return      Renders 200 or 400
     */
    def save() {
        // validate JSON data
        if (request.JSON.name == null || request.JSON.email == null || request.JSON.password == null || request.JSON.picture == null){
            render (status: 400)
            return
        }

        // get JSON data
        String name = request.JSON.name
        String email = request.JSON.email
        String password = request.JSON.password
        String picture = request.JSON.picture.toString()


        def newUser = new User(name, picture)
        def newAccount = new Account(email, password)
        newUser.setAccount(newAccount)
        newAccount.setUser(newUser)

        //validate new user and account
        if (!newUser.validate()){
            render (status: 400)
            return
        }

        if (!newAccount.validate()){
            render (status: 400)
            return
        }

        //add to default group
        //TEMP
        def defaultGroup = UserGroup.findById(1)
        newUser.addToCircles(defaultGroup)
        defaultGroup.addToUsers(newUser)

        // Success
        newAccount.save()
        newUser.save()
        defaultGroup.save(flush:true)

        render (status: 200)
    }

    /**
     * Default DELETE action for user
     * Accessed at /user/id
     * @param id    Id of user
     * @return      Renders 400, 404 or 200
     */
    def delete() {
        // id does not exist
        if (params.id == null){
            render (status: 400)
            return
        }

        // find user
        def user
        try{
            user = User.findById(Long.parseLong(params.id.toString()))
        } catch (NumberFormatException e){
            render(status: 400)
            return
        }

        // user not found
        if (user == null){
            render (status: 404)
            return
        }

        //success
        user.getCircles().each {
            it.removeFromUsers(user)
            if (it.users.empty){
                it.delete(flush: true)
            }
        }
        user.getAccount().delete(flush: true)
        render (status: 200)


    }

    /**
     * Renders a given user
     * @param id    Id of user
     * @return      Renders user or 404
     */
    private renderUser(long id) {

        def user = User.findById(id)
        // user not found
        if (user == null){
            render (status: 404)
            return
        }

        // build json

        render (user as JSON)
    }



}
