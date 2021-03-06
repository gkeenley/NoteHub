package notehub

import org.grails.web.json.JSONObject


/**
 * A class that represents a post in NoteHub
 * @author Cameron Nicolle
 */
class Post {

    /**
     * Constructor for Post
     * @param title     Title of post
     * @param content   Content of post
     */
    Post(String title, String content) {
        this.setTitle(title)
        this.setTime(new Date())
        this.setContent(content)
    }

    Date time
    String title
    String content

    static hasOne = [author: User, group: UserGroup]
    static hasMany = [stars: UserStar, comments: Comment]

    /**
     * Checks if a given Account has access
     * @param a Account to check
     * @return  Whether a Account has access of not
     */
    boolean hasAccess(Account a){
        return (this.getAuthorId() == a.getUserId()) || this.getGroup().hasAccess(a)
    }

    static constraints = {
        time(nullable: false)
        title(nullable: false)
        content(nullable: false)
        author(nullable: false)
        group(nullable: false)
        stars(nullable: true)
        comments(nullable:true)
    }

    static mapping = {
        stars(cascade: "all-delete-orphan")
        comments(cascade: "all-delete-orphan")
    }

    @Override
    String toString() {
        JSONObject json = new JSONObject()
        json.put("id", this.getId().toString())
        return json.toString()
    }
}
