import SwiftUI
import Shared

struct TabCatalogPage: View {
    
    private let component: TabCatalogComponent
    

    init(_ component: TabCatalogComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("TabCatalogPage")
        }
    }
}
