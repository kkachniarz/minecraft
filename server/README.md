## Start server

Run following command to start server: 

    java -Xmx1024M -jar craftbukkit-1.8.8.jar -o true

## Connect

Use in Multiplayer -> Direct Connect

    localhost:25565

## Deploy plugin

Right click Project and select `Run As -> Maven Build`. The generated artifact appears after a few
seconds as `Homecraft/target/Homecraft-0.0.1-SNAPSHOT.jar`. Drag and drop that that file into the
`server/plugins` folder and either restart the server or run the `reload` command in the minecraft
console.
