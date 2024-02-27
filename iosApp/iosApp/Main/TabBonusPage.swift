import SwiftUI
import Shared

struct TabBonusPage: View {
    
    private let component: TabBonusComponent
    

    init(_ component: TabBonusComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("TabBonusPage")
        }
    }
}
