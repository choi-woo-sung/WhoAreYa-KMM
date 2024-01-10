import SwiftUI
import NMapsMap
import shared

@main
struct iOSApp: App {
    init(){KoinKt.doInitKoin()}
	var body: some Scene {
		WindowGroup {
            MapView()
		}
	}
}


struct MapView: View {
    @ObservedObject var busRouteViewModel: MapViewModel
    
    var body: some View {
        VStack {
            NaverMap().ignoresSafeArea(.all, edges: .top)

        }
    }
}


struct NaverMap: UIViewRepresentable {

    func makeCoordinator() -> Coordinator {
        Coordinator.shared
    }

    func makeUIView(context: Context) -> NMFNaverMapView {
        context.coordinator.getNaverMapView()
    }

    func updateUIView(_ uiView: NMFNaverMapView, context: Context) {}

}

final class Coordinator: NSObject, ObservableObject, NMFMapViewCameraDelegate, NMFMapViewTouchDelegate, CLLocationManagerDelegate {
    static let shared = Coordinator()

    let view = NMFNaverMapView(frame: .zero)

    func getNaverMapView() -> NMFNaverMapView {
        view
    }

    override init() {
            super.init()

            view.mapView.positionMode = .direction
            view.mapView.isNightModeEnabled = true

            view.mapView.zoomLevel = 15
            view.mapView.minZoomLevel = 10 // 최소 줌 레벨
            view.mapView.maxZoomLevel = 17 // 최대 줌 레벨

            view.showLocationButton = true
            view.showZoomControls = true // 줌 확대, 축소 버튼 활성화
            view.showCompass = false
            view.showScaleBar = false

            view.mapView.addCameraDelegate(delegate: self)
            view.mapView.touchDelegate = self
        }

    func mapView(_ mapView: NMFMapView, cameraWillChangeByReason reason: Int, animated: Bool) {
        // 카메라 이동이 시작되기 전 호출되는 함수
    }

    func mapView(_ mapView: NMFMapView, cameraIsChangingByReason reason: Int) {
        // 카메라의 위치가 변경되면 호출되는 함수
    }

    func setMarker(_ lat: Double, _ lng: Double) {
        let marker = NMFMarker()
        marker.position = NMGLatLng(lat: lat, lng: lng)
        marker.mapView = view.mapView
    }
}
