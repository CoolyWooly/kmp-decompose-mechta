import SwiftUI
import Shared

struct OnBoardingPage: View {
    
    private let component: OnBoardingComponent
    
    init(_ component: OnBoardingComponent) {
        self.component = component
    }
    
    var items: [OnBoardingItem] = itemsData
    @State var index = 0
    
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
                TabView (selection: $index) {
                    ForEachIndexed(itemsData) { index, item in
                        OnBoardingCard(item: item)
                    }
                }
                .tabViewStyle(PageTabViewStyle())
                .padding(.vertical, 20)
                .onAppear {
                    setupAppearance()
                }
                .frame(height: geo.size.height * 0.9)
                
                
                if (index == itemsData.count - 1) {
                    OnBoardingButton(click: { component.onEvent(event: OnBoardingEventOnButtonPress()) } )
                        .frame(height: geo.size.height * 0.1)
                    
                } else {
                    EmptyView()
                        .frame(height: geo.size.height * 0.1)
                }
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
    
    @State private var isAnimating: Bool = false
    
    var body: some View {
        ZStack {
            VStack(spacing: 20) {
                Image(item.image)
                    .resizable()
                    .scaledToFit()
                    .shadow(color: Color(red: 0, green: 0, blue: 0, opacity: 0.15), radius: 8, x: 6, y: 8)
                    .scaleEffect(isAnimating ? 1.0 : 0.6)
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
        .onAppear {
            withAnimation(.easeOut(duration: 0.5)) {
                isAnimating = true
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
