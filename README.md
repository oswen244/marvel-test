# marvel-test

## Layers:

### View:
* CharacterListActivity: Shows the character list and the search tool
* CharacterDetailActivity: Shows a character information

### ViewModel:
* CharacterViewModel: Handle the liveData events to notify the view (i.e: When fetches a character list from repository)

### Data (Core Module):
* Domain: 
  - GetCharacterUseCase: UseCase for retrieving characters list from repository
  - GetCharacterComicsUseCase: UseCase for retrieving comics where each character appears

* Repository:  
  - CharacterRepository: This is where all data sources lives, in this case remote (from the API) and local (Shared Preferences). Abstracts the data sources so it can be change easily.

* RemoteDatasource:
  - ServiceCharacter: Retrofit interface with API service from MARVEL
  - RemoteDataSourceImp: Performs the service API implementation
 
* LocalDataSource:
  - LocalDataSourceImp: Performs the local call to shared preferences in order to get previously saved data
  
* Models: Keeps the Entities information
  - CharacterEntity: Entity of the character
  - ComicEntity: Entity of the comic
  - ThumnailEntity: Entity for handle images from the API response
  - ComicUriEntity: Entity for handle aditional information of the comics
  - CharacterListResponse: Entity for handle API
  
  
