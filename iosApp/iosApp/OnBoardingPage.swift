import SwiftUI
import Shared

struct OnBoardingPage: View {
    
    private let component: OnBoardingComponent
    

    init(_ component: OnBoardingComponent) {
        self.component = component
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("OnBoardingPage")
            
            Button("ПОНЯТНО", action: { component.onEvent(event: OnBoardingEventOnButtonPress()) })
        }
    }
}
