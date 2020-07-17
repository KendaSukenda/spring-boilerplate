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
            { role: "readWrite", db: "boilerplate-spring" }
        ]
    }
)

// Update user
db.updateUser("boilerplate",
    {
        roles: [
            { role: "dbOwner", db: "boilerplate-spring" }
        ]
    }
)

// Connect to mongodb with username, password & database selected
// mongo --username boilerplate --password boilerplate --authenticationDatabase boilerplate-spring

// Create collections
db.createCollection('users');
db.createCollection('roles');
db.createCollection('menus');
