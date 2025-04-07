## Github API Project
Projekt służy do integracji z publicznym API Githuba, który pozwala na pobieranie informacji o 
repozytoriach użytkownika. Aplikacja filtruje repozytoria, aby zwrócić jedynie te, które nie są forkami, a dla każdego z nich zwraca także informacje o gałęziach i ostatnim commicie.

## Funkcjonalności
  Aplikacja udostępnia jeden endpoint API, który:
    - Pobiera listę repozytoriów użytkownika Githuba, które nie są forkami.
    - Zawiera dla każdego repozytorium nazwę, login właściciela, gałęzie oraz SHA ostatniego commita dla każdej z gałęzi.

  Jeśli użytkownik nie istnieje lub nie ma repozytoriów, zwraca odpowiedni komunikat o błędzie w formacie JSON.

## Endpoints 
   GET /api/github/repos/{user} \n
   Zwraca listę repozytoriów użytkownika Githuba, które nie są forkami, wraz z informacjami o gałęziach i ostatnim commicie.

  - Odpowiedzi:
  1. 200 OK: Zwraca listę repozytoriów.
  2. 404 Not Found: Użytkownik nie istnieje na Githubie lub nie ma repozytoriów.

  # Przykładowa odpowiedź:
        [
          {
          "repoName": "repo-name",
          "owner": {
            "login": "owner-login"
          },
          "branches": [
            {
              "branchName": "main",
              "commit": {
                "sha": "commit-sha"
              }
            }
          ]
        }
      ]
  # Przykładowa odpowiedź dla błędu:

    {
      "status": 404,
      "message": "User not found."
    }

  ## Uruchamianie projektu
   # Klonowanie repozytorium:
  - git clone https://github.com/darl1ng-design/Github-Api-Project.git
      
   # Budowanie i uruchamianie aplikacji:
  Aby zbudować i uruchomić aplikację, użyj Mavena:
  - cd Github-Api-Project
  - mvn clean install
  - mvn spring-boot:run
    
   # Dostępność: Aplikacja będzie działała na lokalnym serwerze pod adresem
   http://localhost:8080/api/github/repos/{user}


  ## Testowanie
  Aplikacja zawiera testy integracyjne, które sprawdzają działanie głównych endpointów. Aby uruchomić testy, użyj poniższej komendy:
   - mvn test

  ## Zależności
  - Spring Boot Starter Web: do budowy aplikacji webowej.
  - Spring Boot Starter Test: do testowania aplikacji.

  ## Struktura projektu
  - GithubClient: Komunikacja z API GitHub.
  - GithubService: Logika aplikacji (pobieranie i filtrowanie repozytoriów).
  - GithubController: Endpoint API do pobierania repozytoriów.
