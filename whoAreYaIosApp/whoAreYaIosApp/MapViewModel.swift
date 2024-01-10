import shared
import KMPNativeCoroutinesAsync

import Foundation

@MainActor
class MapViewModel: ObservableObject {
    @Published private(set) var listStops: [Location] = []
    
    private let clothRepository: ClothRepository
    
    init(repository: ClothRepository) {
        self.clothRepository = repository
        Task {
            do {
                let stream = asyncSequence(for: repository.getClothingCollectionBoxInfo())
                for try await data in stream {
                    print(data)
                }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
    

    
}



struct Location {
    var property1: Double
    var property2: Double
    // Add more properties as needed
    
    init(property1: Double, property2: Double /* Add parameters for additional properties */) {
        self.property1 = property1
        self.property2 = property2
        // Initialize additional properties
    }
}

