import SwiftUI
import Shared

struct OnBoardingPage: View {
    
    private let component: OnBoardingComponent
    
    var items: [OnBoardingItem] = itemsData
    
    @StateValue
    private var state: OnBoardingState
    
    init(_ component: OnBoardingComponent) {
        self.component = component
        _state = StateValue(component.state)
    }
    
    var body: some View {

//     .watch { [weak self] viewAction in
//         guard let self = self, let viewAction = viewAction else { return }
//
//         switch viewAction {
//             case let args as TestAction.OpenDetail:
//                 self.presentDetail(param: args.param)
//
//             default: break
//         }
//     }

        GeometryReader { geo in
            VStack {
//                TabView (selection: .constant(1)) {
//                    ForEachIndexed(itemsData) { index, item in
//                        OnBoardingCard(item: item)
//                    }
//                }
//                .tabViewStyle(PageTabViewStyle())
//                .padding(.vertical, 20)
//                .onAppear {
//                    setupAppearance()
//                }
//                .frame(height: geo.size.height * 0.9)
                
                Text("asdf" + String(state.selectedIndex))
                            
                
//                if (index == itemsData.count - 1) {
//                    OnBoardingButton(click: { component.onEvent(event: OnBoardingEventOnNextClick()) } )
//                        .frame(height: geo.size.height * 0.1)
//                    
//                } else {
//                    EmptyView()
//                        .frame(height: geo.size.height * 0.1)
//                }
            }
        }
    }
}

func setupAppearance() {
    UIPageControl.appearance().currentPageIndicatorTintColor = .black
    UIPageControl.appearance().pageIndicatorTintColor = UIColor.black.withAlphaComponent(0.2)
}

private struct OnBoardingCard: View {
    
    let item: OnBoardingItem
    
    
    var body: some View {
        ZStack {
            VStack(spacing: 20) {
                Image(item.image)
                    .resizable()
                    .scaledToFit()
                    .shadow(color: Color(red: 0, green: 0, blue: 0, opacity: 0.15), radius: 8, x: 6, y: 8)
                    .background(Color.red)
                
                Text(item.title)
                    .foregroundColor(Color.black)
                    .font(.largeTitle)
                    .fontWeight(.heavy)
                
                Text(item.text)
                    .foregroundColor(Color.black)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 16)
                    .frame(maxWidth: 480)
                
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
        .cornerRadius(20)
        .padding(.horizontal, 20)
    }
}

private struct OnBoardingButton: View {
    var click : () -> ()
    
    var body: some View {
        Button("ПОНЯТНО", action: click)
    }
}
