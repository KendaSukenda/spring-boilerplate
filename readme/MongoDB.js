// https://github.com/ProgrammerZamanNow/belajar-mongodb/blob/master/scripts
// use admin database
// use admin

db.createUser(
    {
        user: "sukenda",
        pwd: "sukenda",
        roles: [
            "userAdminAnyDatabase",
            "readWriteAnyDatabase"
        ]
    }
)

// Connect to mongodb with username & password
// mongo --username sukenda --password sukenda --host localhost --port 27017

db.createUser(
    {
        user: "boilerplate",
        pwd: "boilerplate",
        roles: [
            {role: "readWrite", db: "boilerplate-spring"}
        ]
    }
)

// Update user
db.updateUser("boilerplate",
    {
        roles: [
            {role: "dbOwner", db: "boilerplate-spring"}
        ]
    }
)

// Connect to mongodb with username, password & database selected
// mongo --username boilerplate --password boilerplate --authenticationDatabase boilerplate-spring

// Create collections
db.createCollection('users');
db.createCollection('roles');
db.createCollection('menus');

db.roles.insertMany([
    {
        "name": "ADMIN",
        "code": "ROLE_ADMIN",
    },
    {
        "name": "USER",
        "code": "ROLE_USER",
    },
    {
        "name": "TEACHER",
        "code": "ROLE_TEACHER",
    },
    {
        "name": "STUDENT",
        "code": "ROLE_STUDENT",
    },
])

db.menus.insertMany([
    {
        "name": "Dashboard",
        "url": "/",
        "icon": "-",
    },
    {
        "name": "Profile",
        "url": "/profile",
        "icon": "-",
    },
    {
        "name": "Setting",
        "url": "/setting",
        "icon": "-",
    }
])