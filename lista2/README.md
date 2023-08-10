#  **Atividade no Android:**
Uma atividade é um componente fundamental no desenvolvimento de aplicativos Android. Ela representa uma tela única com uma interface do usuário. As atividades são usadas para interagir com os usuários e responder às suas ações. Cada tela em um aplicativo Android é geralmente implementada como uma atividade. As atividades podem ser empilhadas umas sobre as outras e controladas pelo ciclo de vida da atividade.

# **Organização dos arquivos do projeto Android:**
Um projeto Android é organizado em várias pastas e arquivos. Alguns dos diretórios principais incluem:
   - `app`: Contém o código-fonte e recursos específicos do aplicativo.
   - `res`: Contém os recursos como layouts XML, strings, imagens, estilos, etc.
   - `src`: Contém o código-fonte Java ou Kotlin do aplicativo.
   - `manifests`: Contém o arquivo `AndroidManifest.xml`, que descreve informações essenciais sobre o aplicativo.

# **Classe R:**
A classe `R` é uma classe gerada automaticamente pelo sistema Android que fornece referências aos recursos do seu aplicativo, como layouts, strings, imagens e IDs de recursos. Ela é usada para acessar e referenciar os recursos dentro do seu código.

# **Vincular um evento a um item de layout:**
Para vincular um evento a um item de layout no Android, você normalmente faz uso dos métodos de tratamento de eventos e dos atributos `android:onClick` nos elementos do layout XML. Aqui está um exemplo de como fazer isso:

   No arquivo XML do layout:
   ```xml
   <Button
       android:id="@+id/meuBotao"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:text="Clique-me"
       android:onClick="meuMetodoDeManipulacao" />
   ```

   Na classe de atividade Java ou Kotlin:
   ```java
   public class MinhaAtividade extends AppCompatActivity {
       // ...

       public void meuMetodoDeManipulacao(View view) {
           // Seu código de manipulação de evento aqui
       }
   }
   ```

# **ADB (Android Debug Bridge):**
ADB é uma ferramenta de linha de comando que permite a comunicação entre um computador e um dispositivo Android. Ele é usado principalmente para depurar aplicativos e realizar tarefas de desenvolvimento, como instalação de aplicativos, transferência de arquivos, acesso ao shell do dispositivo, captura de logs e muito mais. A comunicação ocorre através de uma conexão USB ou Wi-Fi entre o computador e o dispositivo Android. O ADB permite que os desenvolvedores executem várias ações no dispositivo sem a necessidade de interação direta com a interface do usuário.
