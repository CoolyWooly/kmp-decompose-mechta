import SwiftUI
import Shared

struct OnBoardingPage: View {
    
    private let component: OnBoardingComponent
    
    var items: [OnBoardingModel] = itemsData
    
    @StateValue
    private var state: OnBoardingState
    
    init(_ component: OnBoardingComponent) {
        self.component = component
        _state = StateValue(component.state)
    }
    
    var body: some View {
        ScrollView {
            LazyHStack {
                PageView()
            }
        }
    }
}

private struct PageView: View {
    var body: some View {
        TabView {
            ForEach(itemsData) { item in
                Text("Result: \(item.title)")
            }
        }
        .tabViewStyle(PageTabViewStyle())
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

private struct OnBoardingItem: View {
    
    var body: some View {
        Text("asdf")
    }
}


private struct OnBoardingButton: View {
    var click : () -> ()
    
    var body: some View {
        Button("ПОНЯТНО", action: click)
    }
}
